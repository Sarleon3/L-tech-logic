package com.example.demo.controller;

import com.example.demo.model.AttributeTypes;
import com.example.demo.model.ProductTypeAttributes;
import com.example.demo.model.ProductTypes;
import com.example.demo.repository.AttributeTypesRepository;
import com.example.demo.repository.ProductTypesRepository;
import com.example.demo.repository.ProductTypeAttributesRepository;
import com.example.demo.service.ProductTypeAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ProductTypeAttributes")
public class ProductTypeAttributesController {

    @Autowired
    private ProductTypeAttributesService productTypeAttributesService;

    @Autowired
    private ProductTypesRepository productTypesRepository;

    @Autowired
    private ProductTypeAttributesRepository ProductTypeAttributesRepository;

    @Autowired
    private AttributeTypesRepository attributeTypesRepository;

    // Добавление атрибутов к типу товара
    @PostMapping("/add-batch")
    public ResponseEntity<?> addAttributesToProductType(@RequestParam String typeName, @RequestBody List<String> attributeTypeNames) {
        try {
            // Получаем ProductTypes по имени
            ProductTypes productType = productTypesRepository.findByTypeName(typeName)
                    .orElseThrow(() -> new RuntimeException("Product type not found"));

            // Получаем список AttributeTypes по именам
            List<AttributeTypes> attributes = attributeTypesRepository.findByAttributeNameIn(attributeTypeNames);

            // Добавляем атрибуты в тип товара
            productTypeAttributesService.addAttributesToProductType(productType, attributes);

            return ResponseEntity.ok("Attributes added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    // Получить список атрибутов по категории (typeName)
    @GetMapping("/attributes-by-category")
    public ResponseEntity<List<String>> getAttributesByCategory(@RequestParam String typeName) {
        // Находим тип товара по имени
        ProductTypes productType = productTypesRepository.findByTypeName(typeName)
                .orElseThrow(() -> new RuntimeException("Product type not found"));

        // Получаем только имена атрибутов для этого типа товара
        List<String> attributeNames = ProductTypeAttributesRepository.findAttributeNamesByProductType(productType);

        return ResponseEntity.ok(attributeNames);
    }
}
