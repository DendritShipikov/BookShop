package com.toto.bookshop.mapper;

import com.toto.bookshop.entities.User;
import com.toto.bookshop.dto.UserData;
import com.toto.bookshop.entities.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserData> {

    @Override
    public User toEntity(UserData userData) {
        User user = new User();
        user.setId(userData.getId());
        user.setPassword(userData.getPassword());
        user.setUsername(userData.getUsername());
        if (userData.isPublisher()) {
            Publisher publisher = new Publisher();
            publisher.setName(userData.getName());
            publisher.setAddress(userData.getAddress());
            publisher.setUser(user);
            user.setPublisher(publisher);
        }
        return user;
    }

    @Override
    public UserData toData(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setPassword(user.getPassword());
        userData.setUsername(user.getUsername());
        Publisher publisher = user.getPublisher();
        if (publisher != null) {
            userData.setPublisher(true);
            userData.setName(publisher.getName());
            userData.setAddress(publisher.getAddress());
        }
        return userData;
    }
    
}