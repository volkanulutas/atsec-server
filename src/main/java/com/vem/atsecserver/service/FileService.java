package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.report.rawproduct.EnumRawProductFileDBType;
import com.vem.atsecserver.entity.report.rawproduct.RawProductFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
public interface FileService {
    RawProductFile store(MultipartFile file, EnumRawProductFileDBType fileDBType, RawProduct rawProduct) throws IOException;

    RawProductFile getFile(Long id);

    Stream<RawProductFile> getAllFilesByFileType(EnumRawProductFileDBType fileDBType);
    Stream<RawProductFile> getAllFilesByRawProduct(RawProduct rawProduct);
}
