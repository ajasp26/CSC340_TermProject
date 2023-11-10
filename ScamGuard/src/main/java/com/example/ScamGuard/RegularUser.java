package com.example.ScamGuard;

import jakarta.persistence.Entity;

@Entity
public class RegularUser extends User {
    // Additional fields and methods specific to regular users can be added here

    public RegularUser() {
        super();
        setRole(Role.REGULAR_USER); // Set the user role to REGULAR_USER
    }

    //
}
