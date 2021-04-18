package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.File;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.repository.FileDBRepository;
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
    private FileDBRepository fileDBRepository;

    @Override
    public File store(byte[] file, EnumFileDBType fileType, RawProduct rawProduct) {
        File FileDB = new File(rawProduct.getId() + "", fileType, "pdf", file);
        return fileDBRepository.save(FileDB);
    }

    // TODO: raw product ile ilişkilendir.
    @Override
    public File store(MultipartFile file, EnumFileDBType fileType) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, fileType, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }

    @Override
    public File getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<File> getAllFilesByFileType(EnumFileDBType fileDBType) {
        return fileDBRepository.findByFileDBType(fileDBType).stream();
    }
}