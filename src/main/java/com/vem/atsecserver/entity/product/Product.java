package com.vem.atsecserver.entity.product;

import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.entity.report.product.ProductFile;
import com.vem.atsecserver.entity.sales.Customer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private EnumProductStatus status; // TODO: Daha sonra sil productStatusDates de var bu bilgi

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ProductStatusDate> productStatusDates;

    @ElementCollection(targetClass = EnumProductPreProcessingType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<EnumProductPreProcessingType> preProcessingType;

    @ElementCollection(targetClass = EnumProductFormType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    @Column
    private List<EnumProductFormType> productFormType;

    @ElementCollection(targetClass = EnumProductGranulationType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    @Column
    private List<EnumProductGranulationType> granulationType;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumProductType type; // TODO: Kemiğin türü Product Code lookup type.

    @Column
    private String definition;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    // @Column(unique = true) // TODO:
    private String secCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<ProductFile> files;

    @OneToOne(targetEntity = Location.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private boolean deleted;
    // NOTE: product group a ihtiyaç var mı? Yok bu ihtiyaç ProductStatus ve donorID ile sağlanır.
}