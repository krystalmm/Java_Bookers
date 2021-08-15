package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.form.BookForm;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BookService service;

    @RequestMapping(method = RequestMethod.GET)
    public String top() {
        return "top";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String index(Model model, BookForm bookForm) {
        model.addAttribute("bookList", service.getAll());
        return "index";
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Long bookId, BookForm bookForm) {
        Optional<Book> bookOpt = service.getBook(bookId);
        Optional<BookForm> bookFormOpt = bookOpt.map(b -> makeBookForm(b));
        if (bookFormOpt.isPresent()) {
            bookForm = bookFormOpt.get();
        }
        model.addAttribute("bookForm", bookForm);
        return "show";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String save(@Validated BookForm bookForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bookList", service.getAll());
            return "index";
        }
        BookDto bookDto = makeBookDto(bookForm);
        service.save(bookDto);
        return "redirect:/books";
    }

    private BookForm makeBookForm(Book book) {
        BookForm bookForm = new BookForm();
        bookForm.setTitle(book.getTitle());
        bookForm.setContent(book.getContent());
        return bookForm;
    }

    private BookDto makeBookDto(BookForm bookForm) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(bookForm.getTitle());
        bookDto.setContent(bookForm.getContent());
        return bookDto;
    }
}
