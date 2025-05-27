package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductTypes;
import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.ProductDTO;
import com.example.demo.service.ProductIdGeneratorService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductsController {


    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductIdGeneratorService productIdGeneratorService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(@RequestParam(required = false) Integer productTypeId,
                                        @RequestParam(required = false) String typeName) {
        List<Products> products;

        // Фильтрация по ID типа продукта или по названию
        if (productTypeId != null) {
            products = productService.getProductsByProductTypeId(productTypeId);
        } else if (typeName != null) {
            products = productService.getProductsByTypeName(typeName);
        } else {
            products = productService.getAllProducts();
        }

        // Преобразуем сущности Product в ProductDTO
        return products.stream().map(product -> {
            ProductTypeDTO productTypeDTO = new ProductTypeDTO(
                    product.getProductType().getProductTypeId(),
                    product.getProductType().getTypeName()
            );

            List<String> images = product.getImages();

            return new ProductDTO(
                    product.getProductId(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getStockQuantity(), // передаем количество товара
                    images,
                    productTypeDTO,
                    product.getDescription()
            );
        }).collect(Collectors.toList());
    }



    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Products product) {
        try {
            productIdGeneratorService.initializeGenerator();

            String uniqueId = productIdGeneratorService.generateUniqueNumber();
            if (uniqueId == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось сгенерировать уникальный ID для продукта");
            }

            Integer productId = Integer.parseInt(uniqueId);
            product.setProductId(productId);

            Products savedProduct = productsRepository.saveAndFlush(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ошибка при добавлении продукта: " + e.getMessage());
        }
    }








    // GET: Получить список всех продуктов по категории
    @GetMapping("/category/{typeName}")
    public List<Products> getProductsByCategory(@PathVariable String typeName) {
        return productsRepository.findByProductTypeTypeName(typeName);
    }

    // GET: Получить продукт по ID
    @GetMapping("/batch")
    public List<Products> getProductsByIds(@RequestParam List<Integer> ids) {
        List<Products> products = productsRepository.findAllById(ids);
        if (products.isEmpty()) {
            throw new RuntimeException("No products found for the given IDs: " + ids);
        }
        return products;
    }
}
