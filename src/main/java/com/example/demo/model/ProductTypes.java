package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "ProductTypes")
public class ProductTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;

    @Column(name = "TypeName", nullable = false)
    private String typeName;

    @ManyToOne
    @JoinColumn(name = "ParentTypeId", nullable = true)
    private ProductTypes parentType;

    // Конструктор для десериализации
    @JsonCreator
    public ProductTypes(
            @JsonProperty("productTypeId") int productTypeId) {
        this.productTypeId = productTypeId;
        this.typeName = typeName;
        this.parentType = parentType;
    }

    // Конструктор без parentType, если он не передается
    public ProductTypes(int productTypeId, String typeName) {
        this.productTypeId = productTypeId;
        this.typeName = typeName;
    }

    public ProductTypes() {

    }

    // Геттеры и сеттеры
    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public ProductTypes getParentType() {
        return parentType;
    }

    public void setParentType(ProductTypes parentType) {
        this.parentType = parentType;
    }
}
