package com.toto.bookshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "authors")
    private String authors;

    @Column(name = "pages")
    private int pages;
    
    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book() {}

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

    public Publisher getPublisher() { return publisher; }

    public void setPublisher(Publisher publisher) { this.publisher = publisher; }
    
}