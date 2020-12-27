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
        Product entity = new Product();
        entity.setDefinition(pPram.getDefinition());
        entity.setName(pPram.getName());
        entity.setInformation(pPram.getInformation());
        entity.setSecCode(pPram.getSecCode());
        entity.setStatus(pPram.getStatus());
        entity.setSplitLength(pPram.getSplitLength());
        entity.setType(pPram.getType());
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product productPar) {
        Optional<Product> byId = productRepository.findById(productPar.getId());
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setDefinition(productPar.getDefinition());
            product.setName(productPar.getName());
            product.setInformation(productPar.getInformation());
            product.setSecCode(productPar.getSecCode());
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