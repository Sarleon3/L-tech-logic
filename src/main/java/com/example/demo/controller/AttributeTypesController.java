package com.example.demo.controller;

import com.example.demo.model.AttributeTypes;
import com.example.demo.service.AttributeTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attribute-types")
public class AttributeTypesController {

    @Autowired
    private AttributeTypesService attributeTypesService;

    // Получить все атрибуты
    @GetMapping("/all")
    public List<AttributeTypes> getAllAttributes() {
        return attributeTypesService.getAllAttributes();
    }

    // Получить атрибут по имени
    @GetMapping("/{name}")
    public Optional<AttributeTypes> getAttributeByName(@PathVariable String name) {
        return attributeTypesService.getAttributeByName(name);
    }

    // Добавить новый атрибут
    @PostMapping("/add")
    public AttributeTypes addAttribute(@RequestBody AttributeTypes attributeTypes) {
        return attributeTypesService.addAttribute(attributeTypes);
    }
}
