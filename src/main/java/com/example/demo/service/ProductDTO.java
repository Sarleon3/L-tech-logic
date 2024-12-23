package com.example.demo.service;

public class ProductDTO {
    private long productId;
    private String productName;
    private double price;
    private double rating;
    private String review;
    private String image;
    private ProductTypeDTO productType; // Связь с типом продукта через DTO

    // Конструктор
    public ProductDTO(long productId, String productName, double price, double rating, String review, String image, ProductTypeDTO productType) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.rating = rating;
        this.review = review;
        this.image = image;
        this.productType = productType;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
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

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDTO productType) {
        this.productType = productType;
    }
}
