package com.vem.atsecserver.payload.file;

/**
 * @author volkanulutas
 * @since 03.01.2021
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
