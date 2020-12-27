package com.vem.atsecserver.entity.pool;

import com.vem.atsecserver.entity.rawproduct.EnumPoolStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Entity(name = "Pool")
@Table(name = "Pool")
public class Pool implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id")
    private List<RawProduct> products;

    @Column
    private long generationDate;

    @Column
    private EnumPoolStatus status;


    public Pool() {
        // default constructor.
    }
}
