package com.vem.atsecserver.payload.file;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
public class FileRequest {
    private MultipartFile file;
    private EnumFileDBType type;

    public FileRequest() {
        // Default constructor.
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public EnumFileDBType getType() {
        return type;
    }

    public void setType(EnumFileDBType type) {
        this.type = type;
    }
}
