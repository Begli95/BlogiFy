package org.example.photoApp.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="id")
    private Long id;
    //@Column(name="title")
    private  String title;
    //@Column(name="anons")
    private  String anons;
    //@Column(name="full_text")
    private  String full_text;
    //@Column(name="views")
    private int views;
    public Post() {
    }

    public Post(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
    }
}
