package com.toto.bookshop.services;

import com.toto.bookshop.dto.CartData;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartService {

    public CartData getCartData(HttpSession session) {
        CartData cartData = (CartData)session.getAttribute("cartData");
        if (cartData == null) {
            cartData = new CartData();
            session.setAttribute("cartData", cartData);
        }
        return cartData;
    }

}