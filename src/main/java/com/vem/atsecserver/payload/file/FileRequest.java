package com.vem.atsecserver.payload.file;

import com.vem.atsecserver.entity.report.rawproduct.EnumRawProductFileDBType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
@NoArgsConstructor
@Data
public class FileRequest {
    private MultipartFile file;
    private EnumRawProductFileDBType type;
}
