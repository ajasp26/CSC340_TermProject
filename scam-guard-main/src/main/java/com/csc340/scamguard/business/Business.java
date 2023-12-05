package com.csc340.scamguard.business;

import com.csc340.scamguard.Client;
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
public class Business extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String email;
    private String url;

    private boolean pending;
    private String password;
    private long phone;
    private String location;


}
