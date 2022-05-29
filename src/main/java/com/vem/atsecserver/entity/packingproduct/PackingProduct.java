package com.vem.atsecserver.entity.packingproduct;

import com.vem.atsecserver.entity.rawproduct.Donor;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PackingProduct")
@Table(name = "PackingProduct")
public class PackingProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String serialNumber;

    @Column
    private EnumPackingProductSize packingProduct;

    @Column
    private int packingProductItem;

    @Column
    private String lot;

    @Column
    private long date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @Column
    private boolean deleted;
}