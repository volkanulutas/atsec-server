package com.vem.atsecserver.entity.user;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Entity
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String definition;

    @ManyToMany(mappedBy = "permissions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Role> roles;

    @Column
    private Boolean deleted;

    public Permission() {
        // Default constructor.
    }

    public Permission(String name, String definition) {
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
