package com.toto.bookshop.controllers;

import com.toto.bookshop.services.UserService;
import com.toto.bookshop.services.BookService;
import com.toto.bookshop.dto.UserData;
import com.toto.bookshop.dto.BookData;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

import java.util.List;

@Controller
public class BookController {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @Autowired
    public void setBookService(BookService bookService) { this.bookService = bookService; }

    @GetMapping("/books")
    public String homeGet(Model model) {
        List<BookData> bookDatas = bookService.findAll();
        model.addAttribute("bookDatas", bookDatas);
        return "books";
    }

    @GetMapping("/addbook")
    public String addBookGet(Model model) {
        model.addAttribute("bookData", new BookData());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBookPost(@ModelAttribute("bookData") @Valid BookData bookData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addbook";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserData userData = (UserData)principal;
        bookData.setUserId(userData.getId());
        bookService.save(bookData);
        return "redirect:/books";
    }

    @GetMapping("/editbook")
    public String editBookGet(@RequestParam Long id, Model model) {
        BookData bookData = bookService.getById(id);
        model.addAttribute(bookData);
        return "editbook";
    }

    @PostMapping("/editbook")
    public String editBookGet(@ModelAttribute("bookData") BookData bookData) {
        bookService.save(bookData);
        return "redirect:/mybooks";
    }

    @GetMapping("/viewbook")
    public String viewBookGet(@RequestParam Long id, Model model) {
        BookData bookData = bookService.getById(id);
        UserData userData = userService.getById(bookData.getUserId());
        model.addAttribute("bookData", bookData);
        model.addAttribute("userData", userData);
        return "viewbook";
    }

    @GetMapping("/mybooks")
    public String myBooksGet(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserData userData = (UserData)principal;
        List<BookData> bookDatas = bookService.getByUser(userData.getId());
        model.addAttribute("bookDatas", bookDatas);
        return "mybooks";
    }
    
}