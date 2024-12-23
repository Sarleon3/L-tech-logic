package com.example.demo.service;

import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productRepository;

    public List<Products> getProductsByProductTypeId(int productTypeId) {
        return productRepository.findByProductType_productTypeId(productTypeId);
    }

    public List<Products> getProductsByTypeName(String typeName) {
        return productRepository.findByProductTypeTypeName(typeName);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }
}
