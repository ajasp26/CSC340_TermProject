package com.csc340.scamguard.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Derek Fox
 */
@Entity
@Table(name = "business")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String email;
    private boolean pending;
    private String password;
    private long phone;
    private String location;

    public Business(String title, String email, String password, boolean pending, long phone, String location) {
        this.title = title;
        this.email = email;
        this.password = password;
        this.pending = pending;
        this.phone = phone;
        this.location = location;
    }

}
