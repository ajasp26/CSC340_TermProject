package com.csc340.scamguard.scam;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
@Builder
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
    @Builder.Default
    @ElementCollection
    private List<String> upvotes = new ArrayList<>();
    @Builder.Default
    @ElementCollection
    private List<String> downvotes = new ArrayList<>();
}
