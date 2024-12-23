package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

public class    ProductTypeAttributesId implements Serializable {

    private ProductTypes productType;
    private AttributeTypes attributeType;

    public ProductTypeAttributesId() {
    }

    public ProductTypeAttributesId(ProductTypes productType, AttributeTypes attributeType) {
        this.productType = productType;
        this.attributeType = attributeType;
    }

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

    // Переопределяем equals и hashCode для корректной работы в качестве составного ключа
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTypeAttributesId that = (ProductTypeAttributesId) o;
        return Objects.equals(productType, that.productType) && Objects.equals(attributeType, that.attributeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, attributeType);
    }
}
