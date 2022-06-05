package com.vem.atsecserver.entity.product;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ProductStatusDate")
@Table(name = "ProductStatusDate")
public class ProductStatusDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column
    private EnumProductStatus productStatus;

    @Column
    private long processDate;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
}
