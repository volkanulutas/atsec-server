package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.DonorConverter;
import com.vem.atsecserver.data.xml.District;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.rawproduct.EnumBloodType;
import com.vem.atsecserver.entity.rawproduct.EnumSex;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import com.vem.atsecserver.service.rawproduct.DonorService;
import com.vem.atsecserver.service.xml.CityDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/donor")
// @Secured("DONOR_PAGE_PERMISSION")
public class DonorController {
    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorConverter donorConverter;

    @Autowired
    private CityDistrictService cityDistrictService;

    @GetMapping("/{id}")
    public ResponseEntity<DonorRequest> getDonorById(@PathVariable("id") String id) throws ParseException {
        Donor donorById = donorService.findDonorById(Long.parseLong(id));
        DonorRequest value = donorConverter.toRequest(donorById);
        return ResponseEntity.ok(Optional.ofNullable(value)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not exists with id", id + "")));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DonorRequest> getDonorByCode(@PathVariable("code") String code) throws ParseException {
        System.err.println("Code guncelleme talebi " + code);
        Donor donorById = donorService.findDonorByCode(code);
        DonorRequest value = donorConverter.toRequest(donorById);
        if (donorById != null) {
            if (code.equals(donorById.getCode())) {
                return new ResponseEntity<DonorRequest>(value, HttpStatus.OK);
            }
        }
        return ResponseEntity.ok(Optional.ofNullable(value)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not exists with code", code + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<DonorRequest> getAllDonors() throws ParseException {
        System.out.println("requested...");
        List<DonorRequest> result = new ArrayList<>();
        List<Donor> all = donorService.getAllDonors();
        for (Donor donor : all) {
            result.add(donorConverter.toRequest(donor));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody DonorRequest donorRequest) throws ParseException {
        System.err.println("request: " + donorRequest.toString()
        );
        Donor existence = donorService.findByCitizenNumber(donorRequest.getCitizenshipNumber());
        if (existence != null && existence.getDeleted() == false) {
            return new ResponseEntity<>(new ApiResponse(false, "Donor citizenship number is already taken!"), HttpStatus.BAD_REQUEST);
        }
        Donor donorRequest1 = donorConverter.toEntity(donorRequest);
        System.err.println("request after converter " + donorRequest1.toString());
        Donor donor = donorService.create(donorRequest1);
        System.err.println("save after " + donor.toString());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(donor.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor created successfully."));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody DonorRequest donorRequest) throws ParseException {
        System.out.println("update raw...");
        System.err.println("code: " + donorRequest.getCode());
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
        boolean isDeletedi = donorService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{donorId}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Donor deleted successfully."));
    }

    @GetMapping(value = "/bloodtype", produces = "application/json")
    public List<String> getBloodTypeList() {
        List<String> result = new ArrayList<>();
        for (EnumBloodType type : EnumBloodType.values()) {
            result.add(type.getName());
        }
        return result;
    }

    @GetMapping(value = "/sex", produces = "application/json")
    public List<String> getSexList() {
        List<String> result = new ArrayList<>();
        for (EnumSex type : EnumSex.values()) {
            result.add(type.getName());
        }
        return result;
    }

    @GetMapping(value = "/cities", produces = "application/json")
    public List<String> getCities() {
        return cityDistrictService.getCities();
    }

    @GetMapping(value = "/district/{city}", produces = "application/json")
    public List<String> getDistrict(@PathVariable("city") String city) {
        List<String> districtList = new ArrayList<>();
        List<District> districts = cityDistrictService.getDistricts(city);
        for (District d : districts) {
            districtList.add(d.getDistrictName());
        }
        return districtList;
    }
}