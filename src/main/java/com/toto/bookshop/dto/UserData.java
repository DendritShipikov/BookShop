package com.toto.bookshop.dto;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Collection;

public class UserData implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String address;

    private boolean publisher;
    
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    
    public void setAddress(String address) { this.address = address; }

    public boolean isPublisher() { return publisher; }

    public void setPublisher(boolean publisher) { this.publisher = publisher; }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         Collection<GrantedAuthority> authorities = new HashSet<>();
         authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
         if (publisher) authorities.add(new SimpleGrantedAuthority("ROLE_PUBLISHER"));
         return authorities;
    }
    
    @Override
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
    
}