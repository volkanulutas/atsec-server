package com.vem.atsecserver.service.packingproduct;

import com.vem.atsecserver.converter.PackingProductConverter;
import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.repository.PackingProductRepository;
import com.vem.atsecserver.service.rawproduct.DonorService;
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

    @Autowired
    private DonorService donorService;

    @Override
    public PackingProduct create(PackingProduct packingProduct) {
        System.err.println("PackingProduct: " + packingProduct.toString());
        PackingProduct product = new PackingProduct();
        product.setDonor(donorService.findDonorByCode(packingProduct.getDonor().getCode()));
        product.setDate(packingProduct.getDate());
        product.setSerialNumber(packingProduct.getSerialNumber());
        product.setPackingProduct(packingProduct.getPackingProduct());
        product.setPackingProductItem(packingProduct.getPackingProductItem());
        product.setLot(packingProduct.getLot());
        return packingProductRepository.save(product);
    }

    @Override
    public PackingProduct update(PackingProduct packingProductParam) {
        Optional<PackingProduct> byId = packingProductRepository.findById(packingProductParam.getId());
        PackingProduct packingProduct = byId.get();
        if (byId.isPresent()) {
            PackingProduct product = new PackingProduct();
            // product.setId(packingProduct.getId());
            product.setDonor(packingProduct.getDonor());
            product.setLot(packingProduct.getLot());
            product.setSerialNumber(packingProduct.getSerialNumber());
            product.setDate(packingProduct.getDate());
            product.setPackingProduct(packingProduct.getPackingProduct());
            product.setPackingProductItem(packingProduct.getPackingProductItem());
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
