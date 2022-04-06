package com.vem.atsecserver.entity.rawproduct;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Donor_Institute")
@Table(name = "Donor_Institute")
public class DonorInstitute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 5)
    private String code;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Donor_Institute_id")
    private List<Donor> donors;

    @Column
    private Boolean deleted;
}
