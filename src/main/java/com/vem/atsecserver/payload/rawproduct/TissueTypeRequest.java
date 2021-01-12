package com.vem.atsecserver.payload.rawproduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
public class TissueTypeRequest {
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;

    private String definition;

    private Boolean deleted;

    public TissueTypeRequest() {
        // Default constructor.
    }

    public TissueTypeRequest(String name, String definition) {
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
