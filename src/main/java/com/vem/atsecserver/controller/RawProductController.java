package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.RawProductConverter;
import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.FileDB;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.file.FileResponse;
import com.vem.atsecserver.payload.file.MessageResponse;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.FileService;
import com.vem.atsecserver.service.rawproduct.RawProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
public class RawProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawProductController.class);

    @Autowired
    private RawProductService rawProductService;

    @Autowired
    private RawProductConverter rawProductConverter;

    @Autowired
    private FileService fileService;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
    public ResponseEntity<MessageResponse> uploadApproveFiles(@RequestParam("file") MultipartFile file,
                                                              @RequestParam("fileType") EnumFileDBType fileType) {
        String message = "";
        try {
            fileService.store(file, fileType);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
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
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileDB fileDB = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    // TODO: Barcode generate
    // TODO: barcode view as pdf
}