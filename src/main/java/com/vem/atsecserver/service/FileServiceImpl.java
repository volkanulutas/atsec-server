package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.FileDB;
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
    public FileDB store(MultipartFile file, EnumFileDBType fileType) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, fileType, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    @Override
    public FileDB getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFilesByFileType(EnumFileDBType fileDBType) {
        return fileDBRepository.findByFileDBType(fileDBType).stream();
    }
}