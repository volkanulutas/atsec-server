package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.File;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
public interface FileService {
    File store(byte[] file, EnumFileDBType fileType, RawProduct rawProduct) throws IOException;

    File store(MultipartFile file, EnumFileDBType fileDBType) throws IOException;

    File getFile(Long id);

    Stream<File> getAllFilesByFileType(EnumFileDBType fileDBType);
}