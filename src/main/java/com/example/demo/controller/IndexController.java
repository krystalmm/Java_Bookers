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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        model.addAttribute("bookForm", makeBookForm(service.getBook(bookId)));
        return "show";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String save(@Validated BookForm bookForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("bookList", service.getAll());
            return "index";
        }
        service.save(makeBookDto(bookForm));
        redirectAttributes.addFlashAttribute("complete", "投稿が保存されました！");
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/delete/{bookId}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        service.delete(bookId);
        redirectAttributes.addFlashAttribute("delete", "投稿が削除されました！");
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/{bookId}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long bookId, BookForm bookForm) {
        model.addAttribute("bookForm", makeBookForm(service.getBook(bookId)));
        return "edit";
    }

    @RequestMapping(value = "/books/{bookId}/edit", method = RequestMethod.POST)
    public String update(@Validated BookForm bookForm, BindingResult result, Model model, @PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("bookForm", bookForm);
            return "edit";
        }
        service.update(makeBookDto(bookForm));
        redirectAttributes.addFlashAttribute("update", "投稿内容が更新されました！");
        return "redirect:/books/{bookId}";
    }

    private BookForm makeBookForm(Book book) {
        BookForm bookForm = new BookForm();
        bookForm.setBookId(book.getBookId());
        bookForm.setTitle(book.getTitle());
        bookForm.setContent(book.getContent());
        return bookForm;
    }

    private BookDto makeBookDto(BookForm bookForm) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(bookForm.getBookId());
        bookDto.setTitle(bookForm.getTitle());
        bookDto.setContent(bookForm.getContent());
        return bookDto;
    }
}
