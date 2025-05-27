package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Products")
public class Products {

    @Id
    private int productId;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "stockQuantity", nullable = false)
    private int stockQuantity;

    @Column(name = "description", length = 8000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "ProductImages", joinColumns = @JoinColumn(name = "productId"))
    @Column(name = "imageUrl")
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "productTypeId", nullable = false)
    private ProductTypes productType;

    @Version
    private Integer version;

    // Геттеры и сеттеры

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    @JsonProperty("productTypeId")
    public void setProductTypeId(int productTypeId) {
        this.productType = new ProductTypes();
        this.productType.setProductTypeId(productTypeId);

    }

    public void setProductType(ProductTypes productType) {
        this.productType = productType; // Устанавливаем полный объект ProductTypes
    }
    public ProductTypes getProductType() {
        return productType;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
