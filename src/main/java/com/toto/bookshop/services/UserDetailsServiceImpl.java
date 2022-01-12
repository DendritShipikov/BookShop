package com.toto.bookshop.services;

import com.toto.bookshop.repositories.UserRepository;
import com.toto.bookshop.dto.UserData;
import com.toto.bookshop.entities.User;
import com.toto.bookshop.entities.Publisher;
import com.toto.bookshop.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.HashSet;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private Mapper<User, UserData> userMapper;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    @Autowired
    public void setUserMapper(Mapper<User, UserData> userMapper) { this.userMapper = userMapper; }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("no username " + username);
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

}