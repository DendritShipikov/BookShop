package com.toto.bookshop.dto;

public class BookData {

    private Long id;

    private String title;

    private String authors;

    private int pages;

    private int amount;

    private Long userId;

    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
    
    public String getAuthors() { return authors; }

    public void setAuthors(String authors) { this.authors = authors; }

    public int getPages() { return pages; }

    public void setPages(int pages) { this.pages = pages; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

}