package com.vem.atsecserver.service.barcodegeneration;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class Country {
    private String code;
    private String name;
    private String url;

    public Country(String code, String name, String url) {
        this.code = code;
        this.name = name;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
