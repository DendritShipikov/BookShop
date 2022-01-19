package com.toto.bookshop.controllers;

import com.toto.bookshop.services.UserService;
import com.toto.bookshop.services.BookService;
import com.toto.bookshop.dto.UserData;
import com.toto.bookshop.dto.BookData;
import com.toto.bookshop.dto.CartData;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/cart")
    public String cartGet(HttpSession session, Model model) {
        CartData cartData = getCartData(session);
        model.addAttribute("bookDatas", cartData.getBookDatas());
        return "cart";
    }

    @PostMapping("/cart")
    public String cartPost(HttpSession session) {
        CartData cartData = getCartData(session);
        List<BookData> bookDatas = cartData.getBookDatas();
        for (BookData bookData : bookDatas) {
            bookData.setAmount(bookData.getAmount() - 1);
            bookService.save(bookData);
        }
        session.setAttribute("CartData", new CartData());
        return "redirect:/thanks";
    }

    @GetMapping("/cartdelete")
    public String cartDeleteGet(@RequestParam Long id, HttpSession session) {
        CartData cartData = getCartData(session);
        cartData.remove(id);
        return "redirect:/cart";
    }

    @GetMapping("/thanks")
    public String thanksGet() {
        return "thanks";
    }

    @GetMapping("/buy")
    public String buyGet(@RequestParam Long id, HttpSession session) {
        BookData bookData = bookService.getById(id);
        CartData cartData = getCartData(session);
        cartData.add(bookData);
        return "redirect:/books";
    }

    private CartData getCartData(HttpSession session) {
        CartData cartData = (CartData)session.getAttribute("cartData");
        if (cartData == null) {
            cartData = new CartData();
            session.setAttribute("cartData", cartData);
        }
        return cartData;
    }
    
}