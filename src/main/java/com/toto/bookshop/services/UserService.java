package com.toto.bookshop.services;

import com.toto.bookshop.repositories.UserRepository;
import com.toto.bookshop.entities.User;
import com.toto.bookshop.entities.Publisher;
import com.toto.bookshop.dto.UserData;
import com.toto.bookshop.mapper.Mapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private Mapper<User, UserData> userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    @Autowired
    public void setUserMapper(Mapper<User, UserData> userMapper) { this.userMapper = userMapper; }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) { this.bCryptPasswordEncoder = bCryptPasswordEncoder; }

    public boolean save(UserData userData) {
        if (userRepository.findByUsername(userData.getUsername()) != null) return false;
        userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        User user = userMapper.toEntity(userData);
        /*
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        if (userData.isPublisher()) {
            Publisher publisher = new Publisher();
            publisher.setName(userData.getName());
            publisher.setAddress(userData.getAddress());
            publisher.setUser(user);
            user.setPublisher(publisher);
        }
        */
        userRepository.save(user);
        return true;
    }
    
    public UserData getById(Long id) {
        User user = userRepository.getById(id);
        UserData userData = userMapper.toData(user);
        /*
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setUsername(user.getUsername());
        userData.setPassword(user.getPassword());
        Publisher publisher = user.getPublisher();
        if (publisher != null) {
            userData.setPublisher(true);
            userData.setName(publisher.getName());
            userData.setAddress(publisher.getAddress());
        }
        */
        return userData;
    }
    
    /*public void delete(UserData userData) {
        userRepository.delete(userMapper.toEntity(userData));
    }*/
    
}