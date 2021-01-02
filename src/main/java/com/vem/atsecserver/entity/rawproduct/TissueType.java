package com.vem.atsecserver.entity.rawproduct;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Entity
@Table(name = "Tissue_Type")
public class TissueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String definition;

    @Column
    private Boolean deleted;

    public TissueType() {
        // Default constructor.
    }

    public TissueType(String name, String definition) {
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
