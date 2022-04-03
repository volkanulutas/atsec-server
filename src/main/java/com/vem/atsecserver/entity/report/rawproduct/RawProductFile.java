package com.vem.atsecserver.entity.report.rawproduct;

import com.vem.atsecserver.entity.rawproduct.RawProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "file")
public class RawProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumRawProductFileDBType fileDBType;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private RawProduct rawProduct;

    @Lob
    private byte[] data;

    public RawProductFile(String name, EnumRawProductFileDBType fileDBType, String type, byte[] data,RawProduct rawProduct) {
        this.name = name;
        this.fileDBType = fileDBType;
        this.type = type;
        this.rawProduct = rawProduct;
        this.data = data;
    }
}