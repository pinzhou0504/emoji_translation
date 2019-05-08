package com.emojiHW.service;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.User;
import com.emojiHW.repository.ConversationRepository;
import com.emojiHW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConversationService {
    @Autowired
    public ConversationRepository conversationRepository;

    public Conversation save(Conversation c) {
        return conversationRepository.save(c);
    }

    public Conversation findById(Long id) {
        return conversationRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        conversationRepository.deleteById(id);
    }

    @Transactional
    public List<Conversation> findConversationByUserId(Long userId) {
        return conversationRepository.findConversationByUserId(userId);
    }

}
