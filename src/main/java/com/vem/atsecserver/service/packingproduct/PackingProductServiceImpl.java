package com.vem.atsecserver.service.packingproduct;

import com.vem.atsecserver.converter.PackingProductConverter;
import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.repository.PackingProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackingProductServiceImpl implements PackingProductService {

    @Autowired
    private PackingProductRepository packingProductRepository;

    @Autowired
    private PackingProductConverter packingProductConverter;

    @Override
    public PackingProduct create(PackingProduct packingProduct) {
        System.err.println("PackingProduct: " + packingProduct.toString());
        PackingProduct product = new PackingProduct();
        product.setDonor(packingProduct.getDonor());
        product.setPartitionId(packingProduct.getPartitionId());
        product.setDate(packingProduct.getDate());
        product.setSize(packingProduct.getSize());
        product.setLot(packingProduct.getLot());
        return packingProductRepository.save(product);
    }

    @Override
    public PackingProduct update(PackingProduct packingProductParam) {
        Optional<PackingProduct> byId = packingProductRepository.findById(packingProductParam.getId());
        PackingProduct packingProduct = byId.get();
        if (byId.isPresent()) {
            PackingProduct product = new PackingProduct();
            product.setId(packingProduct.getId());
            product.setDonor(packingProduct.getDonor());
            product.setLot(packingProduct.getLot());
            product.setPartitionId(packingProduct.getPartitionId());
            product.setDate(packingProduct.getDate());
            product.setSize(packingProduct.getSize());
            packingProductRepository.save(packingProduct);
        }
        return null;
    }

    @Override
    public List<PackingProduct> getAllPackingProducts() {
        return packingProductRepository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public PackingProduct delete(Long id) {
        Optional<PackingProduct> byId = packingProductRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            packingProductRepository.save(byId.get());
            return byId.get();
        }
        return null;
    }

    @Override
    public PackingProduct findProductById(Long id) {
        return packingProductRepository.findById(id).get(); // TODO: get()
    }
}
