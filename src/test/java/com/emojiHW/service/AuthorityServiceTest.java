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

    @Test
    @Transactional
    public void findAuthorityByUserId(){

        User u = new User();
        Long userId = u.getId();
        List<Authority> testAuthority = authorityService.findAuthorityByUserId(userId);
        assertNotNull(testAuthority);
        assertEquals(testAuthority.size(),0);
    }
}
