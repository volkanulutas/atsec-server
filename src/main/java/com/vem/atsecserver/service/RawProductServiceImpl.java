package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.repository.RawProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RawProductServiceImpl implements RawProductService {
    @Autowired
    private RawProductRepository rawProductRepository;

    @Override
    public RawProduct create(RawProduct pPram) {
        RawProduct entity = new RawProduct();
        entity.setDefinition(pPram.getDefinition());
        entity.setLocation(pPram.getLocation());
        entity.setDonor(pPram.getDonor());
        entity.setAcceptanceDate(pPram.getAcceptanceDate());
        entity.setInformation(pPram.getInformation());
        entity.setStatus(pPram.getStatus());
        entity.setType(pPram.getType());
        return rawProductRepository.save(entity);
    }

    @Override
    public RawProduct update(RawProduct productPar) {
        Optional<RawProduct> byId = rawProductRepository.findById(productPar.getId());
        if (byId.isPresent()) {
            RawProduct raw = byId.get();
            raw.setDonor(productPar.getDonor());
            raw.setLocation(productPar.getLocation());
            raw.setDefinition(productPar.getDefinition());
            raw.setAcceptanceDate(productPar.getAcceptanceDate());
            raw.setInformation(productPar.getInformation());
            raw.setStatus(productPar.getStatus());
            raw.setType(productPar.getType());
            return rawProductRepository.save(raw);
        }
        return null;
    }

    @Override
    public List<RawProduct> getAllRawProducts() {
        return rawProductRepository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public RawProduct delete(Long id) {
        Optional<RawProduct> byId = rawProductRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            rawProductRepository.save(byId.get());
            return byId.get();
        }
        return null;
    }

    @Override
    public RawProduct findRawProductById(Long id) {
        return rawProductRepository.findById(id).get(); // TODO: get()
    }

    @Override
    public List<String> getRawProductLocations() {
        // TODO: read from file.
        return Arrays.asList(new String[]{"A-1", "A-2"});
    }
}