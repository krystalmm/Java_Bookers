package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }
}

