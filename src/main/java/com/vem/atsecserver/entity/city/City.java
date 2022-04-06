package com.vem.atsecserver.entity.city;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Builder
@Entity(name = "City")
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<District> districtEntities;
}
