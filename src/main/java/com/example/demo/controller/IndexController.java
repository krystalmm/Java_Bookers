package com.example.demo.controller;

import com.example.demo.form.BookForm;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value="/book", method = RequestMethod.GET)
    public String index(Model model, BookForm bookForm) {
        model.addAttribute("bookList", service.getAll());
        return "index";
    }
}
