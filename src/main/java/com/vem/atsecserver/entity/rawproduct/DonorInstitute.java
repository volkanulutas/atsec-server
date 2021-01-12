package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.rawproduct.RawProduct;

import javax.persistence.*;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Entity(name = "Donor_Institute")
@Table(name = "Donor_Institute")
public class DonorInstitute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "donor_institute_id")
    private List<RawProduct> rawProducts;

    @Column
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RawProduct> getRawProducts() {
        return rawProducts;
    }

    public void setRawProducts(List<RawProduct> rawProducts) {
        this.rawProducts = rawProducts;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
