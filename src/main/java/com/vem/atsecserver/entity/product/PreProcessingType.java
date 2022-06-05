package com.vem.atsecserver.entity.product;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PreProcessingType")
@Table(name = "PreProcessingType")
public class PreProcessingType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private EnumProductPreProcessingType preProcessingType;

    @Column
    private long processingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public PreProcessingType(EnumProductPreProcessingType preProcessingType, long processingDate) {
        this.preProcessingType = preProcessingType;
        this.processingDate = processingDate;
    }
}
