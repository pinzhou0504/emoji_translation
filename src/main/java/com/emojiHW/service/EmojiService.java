package com.emojiHW.service;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import com.emojiHW.domain.User;
import com.emojiHW.repository.EmojiRepository;
import com.emojiHW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmojiService {
    @Autowired
    public EmojiRepository emojiRepository;

    public Emoji findById(Long id) {
        return emojiRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        emojiRepository.deleteById(id);
    }

    public Emoji findByCodeIgnoreCase(String code) {
        return emojiRepository.findByCodeIgnoreCase(code);
    }

    public Emoji save(Emoji e) {
        return emojiRepository.save(e);
    }

    @Transactional
    public Emoji findByConversationId(Long conversationId) {
        return emojiRepository.findByConversationId(conversationId);
    }
}
