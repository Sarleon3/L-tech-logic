package com.example.demo.service;

import java.util.List;

public class ProductDTO {
    private long productId;
    private String productName;
    private double price;
    private int stockQuantity;
    private List<String> images;
    private ProductTypeDTO productType; // Связь с типом продукта через DTO
    private String description;

    // Конструктор
    public ProductDTO(long productId, String productName, double price, int stockQuantity, List<String> images, ProductTypeDTO productType, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.images = images;
        this.productType = productType;
        this.description = description;
    }

    // Геттеры и сеттеры
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDTO productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
