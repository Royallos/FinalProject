package com.example.newsportal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Meeting> meetings;
}
