package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.report.rawproduct.RawProductFile;
import com.vem.atsecserver.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "rawproduct")
@Entity
public class RawProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @OneToOne(targetEntity = TissueType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "tissue_type_id")
    private TissueType tissueType;

    /*
    @ManyToOne(fetch = FetchType.EAGER)
    private DonorInstitute donorInstitute;
    */
    @Column
    private long issueTissueDate; // Doku çıkarım tarihi

    @Column
    private long arrivalDate; // Merkeze geliş tarihi

    @OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private String doctorName;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "secuser_id")
    private User checkedOutBy;

    @Column
    private EnumRawProductStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="file_id")
    private List <RawProductFile> files;

    @Column
    private String definition;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    @Column
    private String responsible;

    @Column
    private Boolean tissueCarryCase;

    @Column
    private Boolean sterialBag;

    @Column
    private Boolean dataLogger;

    @Column
    private int temperature;

    @Column
    private Boolean deleted;
}