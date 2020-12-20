package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product pPram) {
        Product product = new Product();
        product.setName(pPram.getName());
        product.setDefinition(pPram.getDefinition());
        product.setDonor(pPram.getDonor());
        product.setExpirationDate(pPram.getExpirationDate());
        product.setInformation(pPram.getInformation());
        product.setSecCode(pPram.getSecCode());
        product.setStatus(pPram.getStatus());
        product.setSplitLength(pPram.getSplitLength());
        product.setType(pPram.getType());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product productPar) {
        Optional<Product> byId = productRepository.findById(productPar.getId());
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setName(productPar.getName());
            product.setDefinition(productPar.getDefinition());
            product.setExpirationDate(productPar.getExpirationDate());
            product.setInformation(productPar.getInformation());
            product.setSecCode(productPar.getSecCode());
            product.setDonor(productPar.getDonor());
            product.setStatus(productPar.getStatus());
            product.setSplitLength(productPar.getSplitLength());
            product.setType(productPar.getType());
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Product delete(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            productRepository.save(byId.get());
            return byId.get();
        }
        return null;
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).get(); // TODO: get()
    }
}