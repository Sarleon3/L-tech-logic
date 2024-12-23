package com.example.demo.repository;

import com.example.demo.model.Products;
import com.example.demo.service.ProductIdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository

public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductIdGeneratorService productIdGeneratorService;

    public Products createProduct(Products product) {
        // Генерация уникального ID
        String uniqueProductId = productIdGeneratorService.generateUniqueNumber();
        product.setProductId(Integer.parseInt(uniqueProductId));  // Устанавливаем ID продукта

        // Сохраняем продукт в базу данных
        entityManager.persist(product);
        return product;
    }
}
