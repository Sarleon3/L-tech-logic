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
import org.springframework.web.bind.annotation.*;

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
            return new ProductDTO(
                    product.getProductId(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getRating(),
                    product.getReview(),
                    product.getImage(),
                    productTypeDTO
            );
        }).collect(Collectors.toList());
    }

    // POST: Добавление нового товара
    @PostMapping("/add")
    public Products addProduct(@RequestBody Products product) {
        // Инициализация генератора (если необходимо)
        productIdGeneratorService.initializeGenerator();

        // Генерация уникального ID для продукта
        String uniqueId = productIdGeneratorService.generateUniqueNumber();
        product.setProductId(Integer.parseInt(uniqueId));


        // Использование merge вместо save
        return productsRepository.save(product);
    }


    // GET: Получить список всех продуктов по категории
    @GetMapping("/category/{typeName}")
    public List<Products> getProductsByCategory(@PathVariable String typeName) {
        return productsRepository.findByProductTypeTypeName(typeName);
    }

    // GET: Получить продукт по ID
    @GetMapping("/{id}")
    public Products getProductById(@PathVariable int id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }
}
