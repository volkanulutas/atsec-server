package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Entity
@Table(name = "Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String definition;

    @Column
    private Boolean deleted;

    public Location() {
        // Default constructor.
    }

    public Location(String name, String definition) {
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
