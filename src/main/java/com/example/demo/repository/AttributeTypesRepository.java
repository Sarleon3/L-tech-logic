package com.example.demo.repository;

import com.example.demo.model.AttributeTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttributeTypesRepository extends JpaRepository<AttributeTypes, Integer> {
    Optional<AttributeTypes> findByAttributeName(String attributeName);
    List<AttributeTypes> findByAttributeNameIn(List<String> attributeNames);
}
