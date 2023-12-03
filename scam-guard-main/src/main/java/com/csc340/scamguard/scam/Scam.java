package com.csc340.scamguard.scam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Kenny Banks
 */
@Entity
@Table(name = "scam")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String posted_by;
    private String associated_business;
    private String target;
    private String method;
    private String location;
    private String description;
    private String posted_on;
    private int flags;
    private List<String> upvotes;
    private List<String> downvotes;
}
