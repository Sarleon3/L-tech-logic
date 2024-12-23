package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private Users user;

    @Column(name = "TotalAmount", nullable = false)
    private double totalAmount;

    public Orders() { }

    public Orders(Users user, double totalAmount) {
        this.user = user;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
