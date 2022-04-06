package com.vem.atsecserver.entity.user;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @Column
    private String definition;

    @Column
    private String menu;

    @ManyToMany(mappedBy = "permissions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Role> roles;

    @Column
    private boolean deleted;

    public Permission(String name, String definition, String menu) {
        this.name = name;
        this.definition = definition;
        this.menu = menu;
    }
}
