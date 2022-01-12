package com.toto.bookshop.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "publishers")
public class Publisher implements java.io.Serializable {

    @Id
    @OneToOne(optional = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Publisher() {}

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

}