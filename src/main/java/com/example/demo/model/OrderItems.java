package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Order_Items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemId")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    public Products product;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    public OrderItems() { }

    public OrderItems(Orders order, Products product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
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
