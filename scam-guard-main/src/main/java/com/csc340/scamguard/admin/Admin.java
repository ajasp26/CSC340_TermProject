package com.csc340.scamguard.admin;

import com.csc340.scamguard.Client;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;
}
