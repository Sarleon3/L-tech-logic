package com.example.demo.service;

import com.example.demo.model.ProductTypeAttributes;
import com.example.demo.model.ProductTypes;
import com.example.demo.model.AttributeTypes;
import com.example.demo.repository.ProductTypeAttributesRepository;
import com.example.demo.repository.ProductTypesRepository;
import com.example.demo.repository.AttributeTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeAttributesService {

    @Autowired
    private ProductTypeAttributesRepository productTypeAttributesRepository;

    @Autowired
    private ProductTypesRepository productTypesRepository;

    @Autowired
    private AttributeTypesRepository attributeTypesRepository;

    // Метод для добавления списка атрибутов в категорию товара
    public void addAttributesToProductType(ProductTypes productType, List<AttributeTypes> attributes) {
        for (AttributeTypes attributeType : attributes) {
            Optional<ProductTypeAttributes> existing = productTypeAttributesRepository.findByProductTypeAndAttributeType(productType, attributeType);
            if (!existing.isPresent()) {
                ProductTypeAttributes productTypeAttributes = new ProductTypeAttributes();
                productTypeAttributes.setProductType(productType);
                productTypeAttributes.setAttributeType(attributeType);
                productTypeAttributesRepository.save(productTypeAttributes);
            }
        }
    }

    // Метод для получения атрибутов по категории товара
    public List<ProductTypeAttributes> getAttributesByProductType(ProductTypes productType) {
        return productTypeAttributesRepository.findByProductType(productType);
    }

    // Метод для получения атрибутов по имени категории товара
    public List<ProductTypeAttributes> getAttributesByProductTypeName(String productTypeName) {
        return productTypeAttributesRepository.findByProductType_TypeName(productTypeName);
    }

    // Метод для удаления атрибута из категории товара
    public void removeAttributeFromProductType(ProductTypes productType, AttributeTypes attributeType) {
        Optional<ProductTypeAttributes> existing = productTypeAttributesRepository.findByProductTypeAndAttributeType(productType, attributeType);
        existing.ifPresent(productTypeAttributesRepository::delete);
    }

    // Метод для получения всех атрибутов по имени атрибута
    public List<ProductTypeAttributes> getAttributesByAttributeName(String attributeName) {
        return productTypeAttributesRepository.findByAttributeType_attributeName(attributeName);
    }
}
