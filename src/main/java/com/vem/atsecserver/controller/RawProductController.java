package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.RawProductConverter;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.report.rawproduct.EnumRawProductFileDBType;
import com.vem.atsecserver.entity.report.rawproduct.RawProductFile;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.file.FileResponse;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.FileService;
import com.vem.atsecserver.service.barcodegeneration.SecBarcodeGeneratorService;
import com.vem.atsecserver.service.rawproduct.DonorInstituteService;
import com.vem.atsecserver.service.rawproduct.DonorService;
import com.vem.atsecserver.service.rawproduct.RawProductService;
import com.vem.atsecserver.service.report.rawproduct.RawProductReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/rawproduct")
// @Secured("RAWPRODUCT_PAGE_PERMISSION")
public class RawProductController {
    @Autowired
    private RawProductService rawProductService;

    @Autowired
    private RawProductConverter rawProductConverter;

    @Autowired
    private FileService fileService;

    @Autowired
    private RawProductReportService rawProductReportService;

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorInstituteService donorInstituteService;

    @Autowired
    private SecBarcodeGeneratorService barcodeGeneratorService;

    @GetMapping("/report/{id}")
    public void generateReport(@PathVariable String id) throws FileNotFoundException, JRException {
        rawProductReportService.exportReport(Long.parseLong(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawProductRequest> getRawProductById(@PathVariable("id") Long id) throws ParseException {
        return ResponseEntity.ok(Optional.ofNullable(rawProductConverter.toRequest(rawProductService.findRawProductById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Raw Product not exists with id", id + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<RawProductRequest> getAllProducts() throws ParseException {
        List<RawProductRequest> result = new ArrayList<>();
        List<RawProduct> all = rawProductService.getAllRawProducts();
        for (RawProduct product : all) {
            result.add(rawProductConverter.toRequest(product));
        }
        return result;
    }

    @GetMapping(value = "/rejectarchives", produces = "application/json")
    public List<RawProductRequest> getRejectArchivesRawProducts() throws ParseException {
        List<RawProductRequest> result = new ArrayList<>();
        List<RawProduct> all = rawProductService.getRawProductsByStatus(EnumRawProductStatus.REJECT);
        for (RawProduct product : all) {
            result.add(rawProductConverter.toRequest(product));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public RawProductRequest create(@RequestBody RawProductRequest productRequest) throws ParseException {
        System.err.println("create-rawproduct");
        RawProduct entity = rawProductConverter.toEntity(productRequest);
        entity = rawProductService.create(entity);
        RawProductRequest rawProductRequest = rawProductConverter.toRequest(entity);
        return rawProductRequest;
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public RawProductRequest update(/*@Valid*/ @RequestBody RawProductRequest productRequest) throws ParseException {
        System.err.println("update-rawproduct");
        RawProduct rawProductRequest = rawProductConverter.toEntity(productRequest);
        RawProduct product = rawProductService.update(rawProductRequest);
        RawProductRequest rawProductRequest1 = rawProductConverter.toRequest(product);
        return rawProductRequest1;
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        RawProduct product = rawProductService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Raw Product deleted successfully."));
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile2(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("fileType") String fileTypeStr,
                                                    @RequestParam("rawProductId") String rawProductId) {
        try {
            System.err.println("Filllleeeee");
            EnumRawProductFileDBType fileType = EnumRawProductFileDBType.find(fileTypeStr);

            RawProduct rawProductById = rawProductService.getRawProductById(Long.parseLong(rawProductId));

            RawProductFile dbFile = fileService.store(file, fileType, rawProductById);

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/upload/")
                    .path(dbFile.getId() + "")
                    .toUriString();

            FileResponse fileResponse = new FileResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    "Dosya başaralı bir şekilde yüklendi.");

            System.err.println("Dosya yükleme basarili.");
            return ResponseEntity.status(HttpStatus.OK).body(fileResponse);
        } catch (Exception e) {
            System.err.println("Dosya yükleme BASARISIZ.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/getFiles/{fileType}")
    public ResponseEntity<List<FileResponse>> getListFiles(@PathVariable("fileType") String fileTypeStr) {
        EnumRawProductFileDBType fileType = EnumRawProductFileDBType.findByName(fileTypeStr);
        List<FileResponse> files = fileService.getAllFilesByFileType(fileType).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId() + "")
                    .toUriString();

            return new FileResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length, "");
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/getFilesByRawProduct/{rawProductId}")
    public ResponseEntity<List<FileResponse>> getListFilesByRawProduct(@PathVariable("rawProductId") String rawProductId) {

        System.err.println("gggggg");
        try {
            RawProduct rawProductById = rawProductService.getRawProductById(Long.parseLong(rawProductId));
            List<FileResponse> files = fileService.getAllFilesByRawProduct(rawProductById).map(dbFile -> {
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/files/")
                        .path(dbFile.getId() + "")
                        .toUriString();

                return new FileResponse(
                        dbFile.getName(),
                        fileDownloadUri,
                        dbFile.getType(),
                        dbFile.getData().length, "");
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(files);


        } catch (Exception ex) {
            System.err.println("sss");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        RawProductFile fileDB = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    // TODO: Barcode generate
    // TODO: barcode view as pdf
    @GetMapping(value = "/barcode", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> download() throws IOException, JRException {

        byte[] barcode = barcodeGeneratorService.getBarcode();

        InputStream pdfFile = new ByteArrayInputStream(barcode);

        // ClassPathResource pdfFile = new ClassPathResource("downloads/" + fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        /*
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + fileName);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
         */
        headers.setContentLength(barcode.length);
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(pdfFile), headers, HttpStatus.OK);
        return response;
    }

}