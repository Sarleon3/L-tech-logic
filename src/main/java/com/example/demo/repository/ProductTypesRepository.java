package com.example.demo.repository;

import com.example.demo.model.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductTypes, Integer> {
    Optional<ProductTypes> findByTypeName(String typeName);
    List<ProductTypes> findByParentTypeIsNull();
    List<ProductTypes> findByParentType_TypeName(String parentTypeName);
}
