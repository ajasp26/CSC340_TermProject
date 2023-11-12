package com.csc340.scamguard.alert;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author csc340-f23
 */
@Entity
@Table(name = "alert")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long posted_by;

    private String name;
    private String method;
    private String location;

    public Alert(int posted_by, String name, String method, String location) {
        this.posted_by = posted_by;
        this.name = name;
        this.method = method;
        this.location = location;
    }

}
