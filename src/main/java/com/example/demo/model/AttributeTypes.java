package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AttributeTypes")
public class AttributeTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attributeTypeId;

    @Column(name = "AttributeName", nullable = false)
    private String attributeName;

    public int getAttributeTypeId() {
        return attributeTypeId;
    }

    public void setAttributeTypeId(int attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
