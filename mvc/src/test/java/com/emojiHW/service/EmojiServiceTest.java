package com.emojiHW.service;

import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class EmojiServiceTest {
    @Autowired
    private EmojiService emojiService;

    @Test
    @Transactional
    public void findByIdTest() {
        Emoji e = new Emoji();
        e.setCode("U+1F600");
        e.setcLDRShortName("grinning_face");
        emojiService.save(e);
        Emoji testEmoji = emojiService.findById(e.getId());
        assertNotNull(testEmoji);
        assertEquals(e.getId(),testEmoji.getId());
    }

    @Test
    @Transactional
    public void findEmojiByCodeIgnoreCase(){
        Emoji e = new Emoji();
        e.setCode("U+1F600");
        e.setcLDRShortName("grinning_face");
        emojiService.save(e);
        Emoji testEmoji = emojiService.findEmojiByCodeIgnoreCase(e.getCode());
        assertNotNull(testEmoji);
        assertEquals(e.getCode(),testEmoji.getCode());
    }

    @Test
    @Transactional
    public void findEmojiByConversationId(){
        Conversation c = new Conversation();
        Long conversationId = c.getId();
        Emoji testEmoji = emojiService.findEmojiByConversationId(conversationId);
        assertNotNull(testEmoji);
        assertEquals(c.getId(),testEmoji.getConversation().getId());

    }
}
