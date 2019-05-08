package com.emojiHW.extend;

import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.User;
import com.emojiHW.repository.UserRepository;
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

import static junit.framework.TestCase.assertNotNull;
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
        userRepository.save(u);
        UserDetails testUser = userDetailsService.loadUserByUsername(u.getUsername());
        assertNotNull(testUser);
        assertEquals(u.getUsername(), testUser.getUsername());

    }
}
