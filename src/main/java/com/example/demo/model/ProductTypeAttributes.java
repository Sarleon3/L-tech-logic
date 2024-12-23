package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductTypeAttributes")
@IdClass(ProductTypeAttributesId.class)  // Указываем составной ключ
public class ProductTypeAttributes {

    @Id
    @ManyToOne
    @JoinColumn(name = "ProductTypeId")
    private ProductTypes productType;

    @Id
    @ManyToOne
    @JoinColumn(name = "AttributeTypeId")
    private AttributeTypes attributeType;

    // Геттеры и сеттеры
    public ProductTypes getProductType() {
        return productType;
    }

    public void setProductType(ProductTypes productType) {
        this.productType = productType;
    }

    public AttributeTypes getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeTypes attributeType) {
        this.attributeType = attributeType;
    }
}
