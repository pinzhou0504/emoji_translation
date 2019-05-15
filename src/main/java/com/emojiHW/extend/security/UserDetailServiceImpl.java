package com.emojiHW.extend.security;

import com.emojiHW.domain.Authority;
import com.emojiHW.domain.User;
import com.emojiHW.service.AuthorityService;
import com.emojiHW.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(username+"is trying to log in from spring security");
//        User domainUser = null;
        User domainUser = userService.findByUsernameIgnoreCase(username);
        List<Authority> userAuthorities = authorityService.findAuthorities(domainUser);
        domainUser.setAuthorities(userAuthorities);
        return domainUser;
    }
//
//    public void save(User user){
//        userService.save(user);
//    }


}
