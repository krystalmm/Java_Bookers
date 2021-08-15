package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_book")
@Data
public class Book {
    
    @Id
    private int bookId;

    private String title;

    private String content;
}
