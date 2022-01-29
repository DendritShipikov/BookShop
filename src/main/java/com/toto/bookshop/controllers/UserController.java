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
import org.springframework.validation.BindingResult;
import org.springframework.security.core.context.SecurityContextHolder;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    private BookService bookService;

    static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @Autowired
    public void setBookService(BookService bookService) { this.bookService = bookService; }

    @GetMapping("/login")
    public String loginGet() {
        LOGGER.info("/login GET request");
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        LOGGER.info("/register GET request");
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("userData") UserData userData, BindingResult bindingResult) {
        LOGGER.info("/register POST request, username=" + userData.getUsername());
        if (!userService.save(userData)) {
            bindingResult.rejectValue("username", null, "User with such name is already exists");
            return "register";
        }
        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String cartGet(HttpSession session, Model model) {
        LOGGER.info("/cart GET request");
        CartData cartData = getCartData(session);
        model.addAttribute("bookDatas", cartData.getBookDatas());
        return "cart";
    }

    @PostMapping("/cart")
    public String cartPost(HttpSession session) {
        LOGGER.info("/cart POST request");
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
        LOGGER.info("/cartdelete POST request");
        CartData cartData = getCartData(session);
        cartData.remove(id);
        return "redirect:/cart";
    }

    @GetMapping("/thanks")
    public String thanksGet() {
        LOGGER.info("/thanks GET request")
        return "thanks";
    }

    @GetMapping("/buy")
    public String buyGet(@RequestParam Long id, HttpSession session) {
        LOGGER.info("/buy GET request, id=" + id);
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