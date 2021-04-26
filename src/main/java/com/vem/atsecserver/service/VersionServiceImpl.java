package com.vem.atsecserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Value("${app.version:0.0.0}")
    private String appVersion;

    @Override
    public String getVersion() {
        return appVersion;
    }
}