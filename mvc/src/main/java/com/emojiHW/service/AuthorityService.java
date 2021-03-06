package com.emojiHW.service;

import com.emojiHW.domain.Authority;
import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.User;
import com.emojiHW.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorityService {
@Autowired
    public AuthorityRepository authorityRepository;

    public Authority save(Authority a) {
        return authorityRepository.save(a);
    }

    @Transactional
    public List<Authority> findAuthorityByUserId(Long userId){
        return authorityRepository.findAuthorityByUserId(userId);
    }

    @Transactional
    public List<Authority> findAuthorities(User domainUser){
        return authorityRepository.findAuthorities(domainUser);
    }

    @Transactional
    public Authority addAuthority(String role, User user){
        Authority a = new Authority();
        a.setRole(role);
        a.setUser(user);
        return authorityRepository.save(a);
    }
}
