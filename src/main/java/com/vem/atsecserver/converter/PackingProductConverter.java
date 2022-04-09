package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.payload.packingproduct.PackingProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class PackingProductConverter {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Autowired
    private DonorConverter donorConverter;

    public PackingProduct toEntity(PackingProductRequest source) throws ParseException {
        PackingProduct target = new PackingProduct();
        target.setDonor(donorConverter.toEntity(source.getDonor()));
        target.setId(source.getId());
        target.setDate(Long.parseLong(source.getDate()));
        target.setSize(EnumPackingProductSize.valueOf(source.getSize()));
        target.setLot(source.getLot());
        target.setPartitionId(source.getPartitionId());
        target.setDeleted(source.isDeleted());
        return target;
    }

    public PackingProductRequest toRequest(PackingProduct source) throws ParseException {
        PackingProductRequest target = new PackingProductRequest();
        target.setId(source.getId());
        System.err.println("ID: " + source.getId());
        target.setDonor(donorConverter.toRequest(source.getDonor()));
        target.setId(source.getId());
        String dateText = simpleDateFormat.format(source.getDate());
        target.setDate(dateText);
        target.setSize(source.getSize().getSize());
        target.setPackingProductCode(source.getSize().getCode());
        target.setLot(source.getLot());
        target.setPartitionId(source.getPartitionId());
        target.setDeleted(source.isDeleted());
        return target;
    }
}