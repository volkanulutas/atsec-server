package com.vem.atsecserver.controller;

import com.vem.atsecserver.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/version")
public class VersionController {
    @Autowired
    private VersionService versionService;

    @GetMapping(value = "/", produces = "application/json")
    public String getVersion() {
        return versionService.getVersion();
    }
}