package com.emojiHW.service;


import com.emojiHW.domain.User;
import com.emojiHW.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public AuthorityService authorityService;
    @Autowired
    private MessageSQSService messageSQSService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser) {
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        authorityService.addAuthority("ROLE_REGISTERED_USER",newUser);
        userRepository.save(newUser);
        return newUser;
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

//    public User findByUsername(String username) {
//        return userRepository.findByUsernameIgnoreCase(username);
//    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsernameIgnoreCase(String username) throws NullPointerException,NotFoundException {
        if (username == null ){
            throw new NotFoundException("username is not found");
        }
        User u = userRepository.findByUsernameIgnoreCase(username);
        if (u == null){
            u = userRepository.findByUsernameIgnoreCaseWithConversation(username);
        }
        if (u == null){
            throw new NullPointerException();
        }
        return u;
    }

    public User findByUsernameIgnoreCaseWithConversation(String username) {
        return userRepository.findByUsernameIgnoreCaseWithConversation(username);
    }

    public List<User> findByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber);
    }


}
