package com.toto.bookshop.entities;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Publisher publisher;

    public User() {}

    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Publisher getPublisher() { return publisher; }

    public void setPublisher(Publisher publisher) { this.publisher = publisher; }

}