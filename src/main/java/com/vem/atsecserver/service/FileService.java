package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
public interface FileService {
    FileDB store(MultipartFile file, EnumFileDBType fileDBType) throws IOException;

    FileDB getFile(Long id);

    Stream<FileDB> getAllFilesByFileType(EnumFileDBType fileDBType);
}
