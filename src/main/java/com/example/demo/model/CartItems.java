package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CartItems")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemId")
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "CartId", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Products product;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    public CartItems() { }

    public CartItems(Cart cart, Products product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
