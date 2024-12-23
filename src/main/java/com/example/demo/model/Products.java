package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Products {

    @Id
    private int productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private double price;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "productTypeId")
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("productTypeId")
    public void setProductTypeId(int productTypeId) {
        this.productType = new ProductTypes();
        this.productType.setProductTypeId(productTypeId);
    }

    // Оставляем стандартный геттер для работы Hibernate
    public ProductTypes getProductType() {
        return productType;
    }
}
