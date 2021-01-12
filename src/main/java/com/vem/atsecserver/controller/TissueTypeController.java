package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.TissueConverter;
import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.payload.rawproduct.TissueTypeRequest;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.service.rawproduct.TissueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 02.01.2021
 */
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/tissuetype")
public class TissueTypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TissueTypeController.class);

    @Autowired
    private TissueService tissueService;

    @Autowired
    private TissueConverter tissueConverter;

    @GetMapping("/{id}")
    public ResponseEntity<TissueTypeRequest> getTissueTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(tissueConverter.toRequest(tissueService.findTissueTypeById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("tissueType not exists with id", id + "")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<TissueTypeRequest> getAllTissueTypes() {
        List<TissueTypeRequest> result = new ArrayList<>();
        List<TissueType> all = tissueService.getAllTissueTypes();
        for (TissueType tt : all) {
            result.add(tissueConverter.toRequest(tt));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody TissueTypeRequest tissueTypeRequest) {
        TissueType tissueType = tissueService.create(tissueConverter.toEntity(tissueTypeRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{tissueTypeId}")
                .buildAndExpand(tissueType.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "tissueType created successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody TissueTypeRequest tissueTypeRequest) {
        TissueType tissueType = tissueService.update(tissueConverter.toEntity(tissueTypeRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{tissueTypeId}")
                .buildAndExpand(tissueType.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "tissueType updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        TissueType tissueType = tissueService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{tissueTypeId}")
                .buildAndExpand(tissueType.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "tissueType deleted successfully."));
    }
}