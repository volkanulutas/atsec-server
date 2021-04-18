package com.vem.atsecserver.service.product;

import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product productPar) {
        Product product = new Product();
        product.setDefinition(productPar.getDefinition());
        product.setInformation(productPar.getInformation());
        product.setSecCode(productPar.getSecCode());
        product.setStatus(productPar.getStatus());
        product.setType(productPar.getType());
        product.setPreProcessingType(productPar.getPreProcessingType());
        product.setDonor(productPar.getDonor());
        product.setCustomer(productPar.getCustomer());
        product.setDeleted(false);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product productPar) {
        Optional<Product> byId = productRepository.findById(productPar.getId());
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setDefinition(productPar.getDefinition());
            product.setInformation(productPar.getInformation());
            product.setSecCode(productPar.getSecCode());
            product.setStatus(productPar.getStatus());
            product.setType(productPar.getType());
            product.setPreProcessingType(productPar.getPreProcessingType());
            product.setDonor(product.getDonor());
            product.setCustomer(productPar.getCustomer());
            product.setDeleted(false);
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