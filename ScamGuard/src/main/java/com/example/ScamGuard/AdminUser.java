package com.example.ScamGuard;

import jakarta.persistence.Entity;

@Entity
public class AdminUser extends User {
    // Additional fields and methods specific to admin users can be added here

    public AdminUser() {
        super();
        setRole(Role.ADMIN); // Set the user role to ADMIN
    }

    // If there are no additional fields or methods for an admin, no further implementation is needed
}
