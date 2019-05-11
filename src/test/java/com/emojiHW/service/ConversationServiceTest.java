package com.emojiHW.service;

import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
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
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class ConversationServiceTest {
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void findByIdTest() {
        Conversation c = new Conversation();
        c.setContent("smilling_face");
        conversationService.save(c);
        Conversation testConversation = conversationService.findById(c.getId());
        assertNotNull(testConversation);
        assertEquals(c.getId(), testConversation.getId());
    }

    @Test
    @Transactional
    public void findConversationByUserIdTest(){

        //在conversation里找user的id，所以要设置user的instance
        User u = new User();
        u.setUsername("SSmith");
//        u.setFirstName("Sam");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
//        u.setAccountNonExpired(true);
//        u.setAccountNonLocked(true);
//        u.setCredentialsNonExpired(true);
//        u.setEnabled(true);
        userService.save(u);
        Long userId = u.getId();
        Conversation c = new Conversation();
        c.setContent("smilling_face");
        c.setUser(u);
        conversationService.save(c);


        List<Conversation> testConversation = conversationService.findConversationByUserId(userId);
        assertNotNull(testConversation);
        assertEquals(testConversation.size(),1);

    }
}
