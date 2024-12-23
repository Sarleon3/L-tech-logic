package com.example.demo.repository;

import com.example.demo.model.Products;
import com.example.demo.service.ProductIdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductIdGeneratorService productIdGeneratorService;

    @Override
    @Transactional  // Обеспечивает выполнение операции в рамках транзакции
    public Products createProduct(Products product) {
        // Генерация уникального ID
        String uniqueProductId = productIdGeneratorService.generateUniqueNumber();
        product.setProductId(Integer.parseInt(uniqueProductId));  // Устанавливаем ID продукта

        // Сохраняем продукт в базу данных
        entityManager.persist(product);
        return product;
    }
}
