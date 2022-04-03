package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.payload.packingproduct.PackingProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class PackingProductConverter {


    public PackingProduct toEntity(PackingProductRequest source) {
        PackingProduct target = new PackingProduct();
        target.setPackingProductCode(source.getPackingProductCode());
        target.setDonor(source.getDonor());
        target.setId(source.getId());
        target.setGamaDate(source.getGamaDate());
        target.setSize(source.getSize());
        target.setLot(source.getLot());
        target.setPartitionId(source.getPartitionId());
        target.setDeleted(source.isDeleted());
       return target;
    }

    public PackingProductRequest toRequest(PackingProduct source){
        PackingProductRequest target = new PackingProductRequest();
        target.setPackingProductCode(source.getPackingProductCode());
        target.setDonor(source.getDonor());
        target.setId(source.getId());
        target.setGamaDate(source.getGamaDate());
        target.setSize(source.getSize());
        target.setLot(source.getLot());
        target.setPartitionId(source.getPartitionId());
        target.setDeleted(source.isDeleted());
        return target;
    }
}