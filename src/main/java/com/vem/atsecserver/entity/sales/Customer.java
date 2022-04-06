package com.vem.atsecserver.entity.sales;

import lombok.*;

import javax.persistence.*;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumCustomerType customerType;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<RawProduct> rawProducts;
     */
    @Column
    private String definition;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String telephone;

    @Column
    private boolean deleted;
}
