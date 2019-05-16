package com.emojiHW.extend;

import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.Authority;
import com.emojiHW.domain.User;
import com.emojiHW.repository.AuthorityRepository;
import com.emojiHW.repository.UserRepository;
import com.emojiHW.service.AuthorityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserDetailServiceImplTest {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AuthorityRepository authorityRepository;

//    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    public void loadUserByUsernameTest(){
        User u = new User();
        u.setUsername("SSmith");
        u.setFirstName("Sam");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCredentialsNonExpired(true);
        u.setEnabled(true);
        userRepository.save(u);
        Authority a = new Authority();
        String role = "ROLE_REGISTERED_USER";
        a.setRole(role);
        a.setUser(u);
        authorityRepository.save(a);
//        List<Authority> authority = authorityService.findAuthorityByUserId(u.getId());
        UserDetails testUser = userDetailsService.loadUserByUsername(u.getUsername());
        assertNotNull(testUser);
//        assertEquals(u.getAuthorities(),testUser.getAuthorities());
        assertEquals(testUser.getAuthorities().size(),1);
    }
}
