package com.vem.atsecserver.entity.packingproduct;

import com.vem.atsecserver.entity.rawproduct.Donor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@NoArgsConstructor
@Data
@Entity(name = "PackingProduct")
@Table(name = "PackingProduct")
public class PackingProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private EnumPackingProductSize size; // TODO: Daha sonra sil productStatusDates de var bu bilgi

    @Column
    private String lot;

    @Column
    private long gamaDate;

    @Column
    private long packingProductCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @Column
    private int partitionId;

    @Column
    private boolean deleted;
}