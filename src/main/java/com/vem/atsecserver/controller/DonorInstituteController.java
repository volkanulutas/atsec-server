package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.DonorInstituteConverter;
import com.vem.atsecserver.entity.rawproduct.DonorInstitute;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.rawproduct.DonorInstituteRequest;
import com.vem.atsecserver.service.rawproduct.DonorInstituteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/donorinstitute")
// @Secured("DONORINSTITUTE_PAGE_PERMISSION")
public class DonorInstituteController {
    @Autowired
    private DonorInstituteService donorInstituteService;

    @Autowired
    private DonorInstituteConverter donorInstituteConverter;

    @GetMapping("/{id}")
    public ResponseEntity<DonorInstituteRequest> getDonorById(@PathVariable("id") Long id) {
       return  ResponseEntity.ok(Optional.ofNullable(donorInstituteConverter.toRequest(donorInstituteService.findDonorById(id)))
               .orElseThrow( ()->new ResourceNotFoundException("Donor Institute not exists with id", id + "") ));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<DonorInstituteRequest> getAllDonorInstitutes() {
        List<DonorInstituteRequest> result = new ArrayList<>();
        List<DonorInstitute> all = donorInstituteService.getAllDonorInstitutes();
        for (DonorInstitute donorInstitute : all) {
            result.add(donorInstituteConverter.toRequest(donorInstitute));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody DonorInstituteRequest donorRequest) {
        System.out.println(donorRequest.toString());

        if (donorInstituteService.existsByCode(donorRequest.getCode())) {
            return new ResponseEntity<>(new ApiResponse(false, "Donor Institute identity number is already taken!"), HttpStatus.BAD_REQUEST);
        }
        DonorInstitute donorInstitute = donorInstituteService.create(donorInstituteConverter.toEntity(donorRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donorInstitute.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor Institute created successfully."));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody DonorInstituteRequest donorInstituteRequest) {
        DonorInstitute donorInstitute = donorInstituteService.update(donorInstituteConverter.toEntity(donorInstituteRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donorInstitute.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor Institute updated successfully."));
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        DonorInstitute donor = donorInstituteService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donor.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor Institute deleted successfully."));
    }
}