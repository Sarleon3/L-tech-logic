package com.example.demo.repository;

import com.example.demo.model.ProductTypeAttributes;
import com.example.demo.model.ProductTypes;
import com.example.demo.model.AttributeTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductTypeAttributesRepository extends JpaRepository<ProductTypeAttributes, Integer> {

    // Метод для поиска связи между ProductType и AttributeType
    Optional<ProductTypeAttributes> findByProductTypeAndAttributeType(ProductTypes productType, AttributeTypes attributeType);

    // Метод для поиска всех атрибутов по типу продукта
    List<ProductTypeAttributes> findByProductType(ProductTypes productType);


    // Получение атрибутов для типа товара (только имена атрибутов)
    @Query("SELECT a.attributeName FROM ProductTypeAttributes pta " +
            "JOIN pta.attributeType a " +
            "WHERE pta.productType = :productType")
    List<String> findAttributeNamesByProductType(ProductTypes productType);

    // Метод для поиска всех атрибутов по имени типа продукта
    List<ProductTypeAttributes> findByProductType_TypeName(String typeName);

    // Метод для поиска всех атрибутов по имени атрибута
    List<ProductTypeAttributes> findByAttributeType_attributeName(String attributeName);

    Collection<Object> findAttributesByProductType(ProductTypes productType);
}
