package org.example.photoApp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private  String title;
    @Column(name="anons")
    private  String anons;
    @Column(name="full_text",length = 1000000)
    private  String full_text;
    @Column(name="views")
    private int views;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name", referencedColumnName = "name")
    @JsonIgnore
    private User user;


    public Post() {
    }

    public Post(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
    }
}
