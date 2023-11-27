package com.csc340.scamguard.admin;

import com.csc340.scamguard.Client;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin") // Ensure this matches the actual table name in the database
public class Admin extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String email;
    private String password;
}
