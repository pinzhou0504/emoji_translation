package com.emojiHW.service;


import com.emojiHW.domain.User;
import com.emojiHW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    public UserRepository userRepository;
    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }
//    List<User> findById();
}
