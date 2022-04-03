package com.vem.atsecserver.payload.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponse {
    private String name;
    private String url;
    private String type;
    private String dataType;
    private long size;
    private String responseMessage;

    public FileResponse(String name, String url, String type, long size, String responseMessage){
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.responseMessage = responseMessage;
    }
}
