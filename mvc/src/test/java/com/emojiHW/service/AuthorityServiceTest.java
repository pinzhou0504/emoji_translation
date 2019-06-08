package com.emojiHW.service;


import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.Authority;
import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class AuthorityServiceTest {
    @Autowired
    public AuthorityService authorityService;
    @Autowired
    public UserService userService;

    @Test
    @Transactional
    public void findAuthorityByUserId(){
        User u = new User();
        u.setUsername("SSmith");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        userService.save(u);
        Long userId = u.getId();
        Authority a = new Authority();
        a.setRole("admin");
        a.setUser(u);
        authorityService.save(a);
        List<Authority> testAuthority = authorityService.findAuthorityByUserId(userId);
        assertNotNull(testAuthority);
        assertEquals(testAuthority.size(),1);
    }

//下面这两个是一个东西！！！一个是在service里面加了addAuthority方法，一个没有加直接在servicetest里面写方法

    @Test
    @Transactional
    public void addAuthorityTest() {
        User u = new User();
        u.setUsername("SSmith");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        userService.save(u);
        Long userId = u.getId();
        Authority a = new Authority();
        String role = "ROLE_REGISTERED_USER";
        a.setRole(role);
        a.setUser(u);
        authorityService.save(a);
        List<Authority> testAuthority = authorityService.findAuthorityByUserId(userId);
        assertNotNull(testAuthority);
        assertEquals(testAuthority.get(0).getAuthority(), role);
    }

    @Test
    @Transactional
    public void AuthorityTest(){
        User u = new User();
        u.setUsername("SSmith");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        String role = "ROLE_REGISTERED_USER";
        authorityService.addAuthority(role,u);
        userService.save(u);
        List<Authority> testAuthority = authorityService.findAuthorityByUserId(u.getId());
        assertNotNull(testAuthority);
        assertEquals(testAuthority.size(),1);
        assertEquals(testAuthority.get(0).getAuthority(),role);

    }
}
