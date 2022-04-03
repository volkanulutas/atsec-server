package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.report.rawproduct.EnumRawProductFileDBType;
import com.vem.atsecserver.entity.report.rawproduct.RawProductFile;
import com.vem.atsecserver.repository.file.rawproduct.RawProductFileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private RawProductFileDBRepository fileDBRepository;

    @Override
    public RawProductFile store(MultipartFile file, EnumRawProductFileDBType fileType, RawProduct rawProduct) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        RawProductFile FileDB = new RawProductFile(fileName, fileType, file.getContentType(), file.getBytes(), rawProduct);
        return fileDBRepository.save(FileDB);
    }

    @Override
    public RawProductFile getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<RawProductFile> getAllFilesByFileType(EnumRawProductFileDBType fileDBType) {
        return fileDBRepository.findByFileDBType(fileDBType).stream();
    }

    @Override
    public Stream<RawProductFile> getAllFilesByRawProduct(RawProduct rawProduct) {
        return fileDBRepository.findByRawProduct(rawProduct);
    }
}