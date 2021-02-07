package com.vem.atsecserver.entity.file;

import com.vem.atsecserver.entity.rawproduct.RawProduct;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "File")
@Table(name = "file")
public class FileDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumFileDBType fileDBType;

    private String type;

    @ManyToMany(mappedBy = "files")
    private List<RawProduct> rawProducts = new ArrayList<>();

    @Lob
    private byte[] data;

    public FileDB() {
        // Default constructor.
    }

    public FileDB(String name, EnumFileDBType fileDBType, String type, byte[] data) {
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

    public List<RawProduct> getRawProducts() {
        return rawProducts;
    }

    public void setRawProducts(List<RawProduct> rawProducts) {
        this.rawProducts = rawProducts;
    }
}
