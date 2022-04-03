package com.vem.atsecserver.entity.rawproduct;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@NoArgsConstructor
@Data
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

    public TissueType(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }
}