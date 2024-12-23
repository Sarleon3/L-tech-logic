package com.example.demo.controller;

import com.example.demo.model.ProductTypes;
import com.example.demo.repository.ProductTypesRepository;
import com.example.demo.service.ProductTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productTypes")
public class ProductTypesController {

    @Autowired
    private ProductTypesRepository productTypesRepository;

    // GET: Retrieve Product Type by Name
    @GetMapping("/byName/{name}")
    public ResponseEntity<ProductTypeDTO> getProductTypeByName(@PathVariable String name) {
        Optional<ProductTypes> productType = productTypesRepository.findByTypeName(name);

        // Если продукт найден, преобразуем его в ProductTypeDTO
        if (productType.isPresent()) {
            ProductTypes product = productType.get();

            ProductTypeDTO productTypeDTO = new ProductTypeDTO(
                    product.getProductTypeId(),
                    product.getTypeName()
            );

            return ResponseEntity.ok(productTypeDTO);
        } else {
            // Если продукт не найден, возвращаем 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // GET: Retrieve all child types by Parent Type Name
    @GetMapping("/children/{parentName}")
    public ResponseEntity<List<ProductTypeDTO>> getChildTypesByParentName(@PathVariable String parentName) {
        List<ProductTypes> childTypes = productTypesRepository.findByParentType_TypeName(parentName);

        // Преобразуем каждый объект ProductTypes в ProductTypeDTO
        List<ProductTypeDTO> response = childTypes.stream()
                .map(productType -> new ProductTypeDTO(
                        productType.getProductTypeId(),
                        productType.getTypeName()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // GET: Retrieve all Product Types with no Parent (Root Categories)
    @GetMapping("/allParent")
    public ResponseEntity<List<ProductTypes>> getRootTypes() {
        List<ProductTypes> rootTypes = productTypesRepository.findByParentTypeIsNull();
        return ResponseEntity.ok(rootTypes);
    }
}
