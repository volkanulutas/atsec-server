package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.DonorConverter;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import com.vem.atsecserver.service.rawproduct.DonorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/donor")
// @Secured("DONOR_PAGE_PERMISSION")
public class DonorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonorController.class);

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorConverter donorConverter;

    @GetMapping("/{id}")
    public ResponseEntity<DonorRequest> getDonorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(donorConverter.toRequest(donorService.findDonorById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Donor not exists with id", id + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<DonorRequest> getAllDonors() {
        List<DonorRequest> result = new ArrayList<>();
        List<Donor> all = donorService.getAllDonors();
        for (Donor donor : all) {
            result.add(donorConverter.toRequest(donor));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody DonorRequest donorRequest) {
        if (donorService.existsByCode(donorRequest.getCode())) {
            return new ResponseEntity<>(new ApiResponse(false, "Donor identity number is already taken!"), HttpStatus.BAD_REQUEST);
        }
        Donor donor = donorService.create(donorConverter.toEntity(donorRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donor.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor created successfully."));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody DonorRequest donorRequest) {
        Donor donor = donorService.update(donorConverter.toEntity(donorRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donor.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor updated successfully."));
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Donor donor = donorService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donor.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor deleted successfully."));
    }
}