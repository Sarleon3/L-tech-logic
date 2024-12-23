package com.example.demo.service;

public class ProductTypeDTO {
    private int productTypeId;
    private String typeName;
    private int parentType; // только ID родительского типа

    // Конструктор
    public ProductTypeDTO(int productTypeId, String typeName) {
        this.productTypeId = productTypeId;
        this.typeName = typeName;
        this.parentType = parentType;
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

    public int getParentType() {
        return parentType;
    }

    public void setParentType(int parentType) {
        this.parentType = parentType;
    }


}
