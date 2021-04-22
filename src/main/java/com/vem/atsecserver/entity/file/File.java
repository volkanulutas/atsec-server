package com.vem.atsecserver.entity.file;

import com.vem.atsecserver.entity.rawproduct.RawProduct;

import javax.persistence.*;


@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumFileDBType fileDBType;

    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private RawProduct rawProduct;

    @Lob
    private byte[] data;

    public File() {
        // Default constructor.
    }

    public File(String name, EnumFileDBType fileDBType, String type, byte[] data) {
        this.name = name;
        this.fileDBType = fileDBType;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumFileDBType getFileDBType() {
        return fileDBType;
    }

    public void setFileDBType(EnumFileDBType fileDBType) {
        this.fileDBType = fileDBType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public RawProduct getRawProduct() {
        return rawProduct;
    }

    public void setRawProduct(RawProduct rawProduct) {
        this.rawProduct = rawProduct;
    }
}
