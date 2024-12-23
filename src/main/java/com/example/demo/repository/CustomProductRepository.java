package com.example.demo.repository;

import com.example.demo.model.Products;

public interface CustomProductRepository {
    Products createProduct(Products product);
}