package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.RawProductConverter;
import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.File;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.file.FileResponse;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.FileService;
import com.vem.atsecserver.service.barcodegeneration.SecBarcodeGeneratorService;
import com.vem.atsecserver.service.rawproduct.RawProductService;
import com.vem.atsecserver.service.report.rawproduct.RawProductReportService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/rawproduct")
// @Secured("RAWPRODUCT_PAGE_PERMISSION")
public class RawProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawProductController.class);

    @Autowired
    private RawProductService rawProductService;

    @Autowired
    private RawProductConverter rawProductConverter;

    @Autowired
    private FileService fileService;

    @Autowired
    private RawProductReportService rawProductReportService;

    @Autowired
    private SecBarcodeGeneratorService barcodeGeneratorService;

    @GetMapping("/report/{id}")
    public void generateReport(@PathVariable String id) throws FileNotFoundException, JRException {
        rawProductReportService.exportReport(Long.parseLong(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawProductRequest> getRawProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(rawProductConverter.toRequest(rawProductService.findRawProductById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Raw Product not exists with id", id + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<RawProductRequest> getAllProducts() {
        List<RawProductRequest> result = new ArrayList<>();
        List<RawProduct> all = rawProductService.getAllRawProducts();
        for (RawProduct product : all) {
            result.add(rawProductConverter.toRequest(product));
        }
        return result;
    }

    @GetMapping(value = "/rejectarchives", produces = "application/json")
    public List<RawProductRequest> getRejectArchivesRawProducts() {
        List<RawProductRequest> result = new ArrayList<>();
        List<RawProduct> all = rawProductService.getRawProductsByStatus(EnumRawProductStatus.MEDICAL_WASTE);
        for (RawProduct product : all) {
            result.add(rawProductConverter.toRequest(product));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody RawProductRequest productRequest) {
        RawProduct product = rawProductService.create(rawProductConverter.toEntity(productRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Raw Product created successfully."));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody RawProductRequest productRequest) {
        RawProduct product = rawProductService.update(rawProductConverter.toEntity(productRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Raw Product updated successfully."));
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
    public ResponseEntity<FileResponse> uploadApproveFiles(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("fileType") EnumFileDBType fileType) {
        try {
            File dbFile = fileService.store(file, fileType);

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
            return ResponseEntity.status(HttpStatus.OK).body(fileResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/getFiles/{fileType}")
    public ResponseEntity<List<FileResponse>> getListFiles(@PathVariable("fileType") EnumFileDBType fileType) {
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

    @ResponseBody
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        File fileDB = fileService.getFile(id);

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