package com.vem.atsecserver.entity.product;

import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.entity.report.product.ProductFile;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.payload.product.PreProcessingTypeRequest;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @Column
    private EnumProductStatus status; // TODO: Daha sonra sil productStatusDates de var bu bilgi

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ProductStatusDate> productStatusDates;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreProcessingType> preProcessingTypes;

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
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<ProductFile> files;

    @OneToOne(targetEntity = Location.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private boolean deleted;

    public void addPreProcessingType(PreProcessingType preProcessingType) {
        if (preProcessingTypes == null) {
            preProcessingTypes = new ArrayList<>();
        }
        preProcessingType.setProduct(this);
        preProcessingTypes.add(preProcessingType);
    }

    public void addProductStatusDates(ProductStatusDate productStatusDate) {
        if (this.productStatusDates == null) {
            this.productStatusDates = new ArrayList<>();
        }
        productStatusDate.setProduct(this);
        this.productStatusDates.add(productStatusDate);
    }
}