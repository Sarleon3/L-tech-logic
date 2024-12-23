package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    // Метод для получения всех продуктов по имени категории
    List<Products> findByProductType_productTypeId(int productTypeId);
    List<Products> findByProductTypeTypeName(String typeName);
}
