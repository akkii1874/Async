package com.async.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String category;
    private String author;
    private String publisher;
    private String edition;

}
