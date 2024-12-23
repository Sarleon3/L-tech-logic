package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Favorites")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteId")
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Products product;

    public Favorites() { }

    public Favorites(Users user, Products product) {
        this.user = user;
        this.product = product;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
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
}
