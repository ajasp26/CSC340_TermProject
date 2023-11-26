package com.csc340.scamguard.admin;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin") // Ensure this matches the actual table name in the database
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true) // Assuming the admin name is unique
    private String name;

    @Column(nullable = false, unique = true) // Assuming the admin email is unique
    private String email;

    @Column(nullable = false)
    private String password; // This should be a hashed password
}
