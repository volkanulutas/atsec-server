package com.vem.atsecserver.payload.file;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
public class FileResponse {
    private String name;
    private String url;
    private String type;
    private long size;
    private String responseMessage;

    public FileResponse() {
        // Default constructor.
    }

    public FileResponse(String name, String url, String type, long size, String responseMessage) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.responseMessage = responseMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
