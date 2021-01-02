package com.vem.atsecserver.payload;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
public class LocationRequest {

    private Long id;

    private String name;

    private String definition;

    private Boolean deleted;

    public LocationRequest() {
        // Default constructor.
    }

    public LocationRequest(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
