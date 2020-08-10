package com.fhoszowski.photogallery.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    @OneToMany(mappedBy = "user")
    List<Gallery> galleries;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String role;

    public String setPassword( String password ) {
        this.password = password;
        return password;
    }
}
