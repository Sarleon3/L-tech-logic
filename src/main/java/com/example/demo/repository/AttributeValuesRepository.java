package com.example.demo.repository;

import com.example.demo.model.AttributeValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeValuesRepository extends JpaRepository<AttributeValues, Integer> {
    List<AttributeValues> findByProduct_ProductId(int productId);
}
