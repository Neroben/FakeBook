package com.example.FakeBook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usr")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    private String urlImage;
    private String email;
    private String name;
    private String password;

    public User() {
    }

    public User(String email, String name, String password, String urlImage) {
        this.urlImage = urlImage;
        this.email = email;
        this.name = name;
        this.password = password;
    }

}

