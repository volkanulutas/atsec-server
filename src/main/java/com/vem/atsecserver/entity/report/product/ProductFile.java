package com.vem.atsecserver.entity.report.product;

import com.vem.atsecserver.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "file")
public class ProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumProductFileDBType fileDBType;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Lob
    private byte[] data;

    public ProductFile(String name, EnumProductFileDBType fileDBType,
                       String type,  byte[] data,  Product product) {
        this.name = name;
        this.fileDBType = fileDBType;
        this.type = type;
        this.product = product;
        this.data = data;
    }
}
