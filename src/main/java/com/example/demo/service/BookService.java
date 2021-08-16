package com.example.demo.service;

import java.util.List;

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
        return bookRepository.findAll();
    }

    public Book getBook(Long bookId) {
        return bookRepository.findByBookId(bookId);
    }

    @Transactional
    public void save(BookDto dto) {
        bookRepository.save(setBook(new Book(), dto));
    }

    @Transactional
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void update(BookDto dto) {
        bookRepository.save(setBook(bookRepository.findByBookId(dto.getBookId()), dto));
    }

    private Book setBook(Book book, BookDto dto) {
        book.setTitle(dto.getTitle());
        book.setContent(dto.getContent());
        return book;
    }
}

