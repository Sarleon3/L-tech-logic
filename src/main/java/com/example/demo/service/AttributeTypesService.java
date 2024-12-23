package com.example.demo.service;

import com.example.demo.model.AttributeTypes;
import com.example.demo.repository.AttributeTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeTypesService {

    @Autowired
    private AttributeTypesRepository attributeTypesRepository;

    // Метод для получения всех атрибутов
    public List<AttributeTypes> getAllAttributes() {
        return attributeTypesRepository.findAll();
    }

    // Метод для добавления атрибута
    public AttributeTypes addAttribute(AttributeTypes attributeTypes) {
        return attributeTypesRepository.save(attributeTypes);
    }

    // Метод для поиска атрибута по имени
    public Optional<AttributeTypes> getAttributeByName(String name) {
        return attributeTypesRepository.findByAttributeName(name);
    }
}
