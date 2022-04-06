package com.vem.atsecserver.entity.rawproduct;

import lombok.*;

import javax.persistence.*;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private EnumLocationType locationType;

    @Column
    private Boolean deleted;

    public Location(String name, String definition, EnumLocationType locationType){
        this.name = name;
        this.definition = definition;
        this.locationType = locationType;
    }
}
