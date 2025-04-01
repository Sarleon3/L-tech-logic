package com.example.demo.controller;

import com.example.demo.model.AttributeValues;
import com.example.demo.model.Products;
import com.example.demo.repository.AttributeValuesRepository;
import com.example.demo.repository.ProductsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    private final AttributeValuesRepository attributeValuesRepository;
    private final ProductsRepository productsRepository;

    public AttributeController(AttributeValuesRepository attributeValuesRepository, ProductsRepository productsRepository) {
        this.attributeValuesRepository = attributeValuesRepository;
        this.productsRepository = productsRepository;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<AttributeValues>> getAttributesByProduct(@PathVariable int productId) {
        List<AttributeValues> attributes = attributeValuesRepository.findByProduct_ProductId(productId);
        return ResponseEntity.ok(attributes);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<List<AttributeValues>> addAttributesToProduct(@PathVariable int productId, @RequestBody List<AttributeValues> attributeValuesList) {
        // Найдем продукт по ID
        Products product = productsRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Создадим список для сохраненных атрибутов
        List<AttributeValues> savedAttributes = new ArrayList<>();

        // Пройдем по каждому элементу в списке атрибутов и привяжем его к продукту
        for (AttributeValues attributeValues : attributeValuesList) {
            attributeValues.setProduct(product); // Привязываем атрибут к продукту
            savedAttributes.add(attributeValuesRepository.save(attributeValues)); // Сохраняем атрибут и добавляем его в список
        }

        // Возвращаем список сохраненных атрибутов
        return ResponseEntity.ok(savedAttributes);
    }

}
