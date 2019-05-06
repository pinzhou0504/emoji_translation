package com.emojiHW.service;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.User;
import com.emojiHW.repository.ConversationRepository;
import com.emojiHW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    @Autowired
    public ConversationRepository conversationRepository;

    public void save(Conversation conversation) { conversationRepository.save(conversation); }

    public Conversation findById(Long id){
        return conversationRepository.findById(id).get();
    }

    public void deleteById(Long id){ conversationRepository.deleteById(id);}

}
