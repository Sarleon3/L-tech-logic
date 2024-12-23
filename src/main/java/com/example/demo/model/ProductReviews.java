package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductReviews")
public class ProductReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewId")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Products product;

    @Column(name = "Rating", nullable = false)
    private int rating;

    @Column(name = "ReviewText")
    private String reviewText;

    @Column(name = "ReviewDate", nullable = false)
    private java.time.LocalDateTime reviewDate = java.time.LocalDateTime.now();

    public ProductReviews() { }

    public ProductReviews(Users user, Products product, int rating, String reviewText) {
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public java.time.LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(java.time.LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
