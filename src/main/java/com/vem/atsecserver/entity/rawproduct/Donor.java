package com.vem.atsecserver.entity.rawproduct;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Donor")
@Table(name = "Donor")
public class Donor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 13)
    private String code; // Filled by DonorCode.class

    @Column(length = 11)
    private String citizenshipNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private DonorInstitute donorInstitute;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private List<RawProduct> rawProducts;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String telephone;

    @Column
    private String address;

    @Column
    private String addressDistrict;

    @Column
    private String addressCity;

    @Column
    private EnumBloodType bloodType;

    @Column
    private EnumSex sex;

    @Column
    private int tissueNumber;

    @Column
    private String tissueType;

    @Column
    private long birthdate;

    @Column
    private long registeredDate;

    @Lob
    private byte[] bloodTestPdfFile;

    @Column
    private Boolean deleted;
}
