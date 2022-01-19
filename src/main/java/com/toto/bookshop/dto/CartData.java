package com.toto.bookshop.dto;

import java.util.List;
import java.util.ArrayList;

public class CartData {

    private List<BookData> bookDatas = new ArrayList<>();

    public List<BookData> getBookDatas() { return bookDatas; }

    public void setBookDatas(List<BookData> bookDatas) { this.bookDatas = bookDatas; }

    public void add(BookData bookData) { bookDatas.add(bookData); }

    public void remove(Long id) {
        for (BookData bookData : bookDatas) {
            if (bookData.getId() == id) {
                bookDatas.remove(bookData);
                return;
            }
        }
    }

}