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
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @Autowired
    public void setBookService(BookService bookService) { this.bookService = bookService; }

    @GetMapping("/login")
    public String loginGet() {
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("userData") UserData userData) {
        userService.save(userData);
        return "redirect:/books";
    }

}