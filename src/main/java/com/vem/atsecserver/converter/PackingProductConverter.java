package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.payload.packingproduct.PackingProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class PackingProductConverter {

    @Autowired
    private DonorConverter donorConverter;


    public PackingProduct toEntity(PackingProductRequest source) throws ParseException {
        PackingProduct target = new PackingProduct();
        target.setPackingProductCode(source.getPackingProductCode());
        target.setDonor( donorConverter.toEntity(source.getDonor()));
        target.setId(source.getId());
        target.setGamaDate(source.getGamaDate());
        target.setSize(source.getSize());
        target.setLot(source.getLot());
        target.setPartitionId(source.getPartitionId());
        target.setDeleted(source.isDeleted());
       return target;
    }

    public PackingProductRequest toRequest(PackingProduct source) throws ParseException {
        PackingProductRequest target = new PackingProductRequest();
        target.setPackingProductCode(source.getPackingProductCode());
        target.setDonor(donorConverter.toRequest(source.getDonor()));
        target.setId(source.getId());
        target.setGamaDate(source.getGamaDate());
        target.setSize(source.getSize());
        target.setLot(source.getLot());
        target.setPartitionId(source.getPartitionId());
        target.setDeleted(source.isDeleted());
        return target;
    }
}