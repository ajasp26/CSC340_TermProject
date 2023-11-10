package com.example.ScamGuard;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class BusinessUser extends User {
    @Column(name = "business_name", nullable = false)
    private String businessName;

    // Default constructor
    public BusinessUser() {
        super();
        setRole(Role.BUSINESS_USER); // Set the user role to BUSINESS_USER
    }

    // Constructor with businessName
    public BusinessUser(String username, String password, String email, String businessName) {
        super(username, password, email, Role.BUSINESS_USER);
        this.businessName = businessName;
    }

    // Business-specific methods and fields can be added here

    // Getters and Setters
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
