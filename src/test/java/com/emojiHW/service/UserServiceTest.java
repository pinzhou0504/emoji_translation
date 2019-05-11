package com.emojiHW.service;


import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void findByIdTest() {
        User u = new User();
        u.setUsername("SSmith");
        u.setFirstName("Sam");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        userService.save(u);
        User testUser = userService.findById(u.getId());
        assertNotNull(testUser);
        assertEquals(u.getId(), testUser.getId());
    }

    @Test
    @Transactional
    public void findByUsernameIgnoreCase(){
        User u = new User();
        u.setUsername("SSmith");
        u.setFirstName("Sam");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        userService.save(u);
        User testUser = userService.findByUsernameIgnoreCase(u.getUsername());
        assertNotNull(testUser);
        assertEquals(u.getUsername(), testUser.getUsername());
    }

    @Test
    @Transactional
    public void findAll(){
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

        userService.createUser(u);
        List<User> testUser = userService.findAll();
        assertNotNull(testUser);
        //存了一个user,和一个test比较，所以size=2
        assertEquals(testUser.size(),1);
        //存的user是第0个，所以0和test比较
        assertEquals(u.getUsername(),testUser.get(0).getUsername());

    }
}
