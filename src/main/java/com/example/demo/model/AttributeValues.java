package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AttributeValues")
public class AttributeValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attributeValueId;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "AttributeTypeId")
    private AttributeTypes attributeType;

    @Column(name = "AttributeValues")
    private String attributeValues;

    public int getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(int attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public AttributeTypes getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeTypes attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(String attributeValues) {
        this.attributeValues = attributeValues;
    }
}
