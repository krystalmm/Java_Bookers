package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    public Optional<Book> getBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book;
    }

    @Transactional
    public void save(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setContent(dto.getContent());
        bookRepository.save(book);
    }
}

