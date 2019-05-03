package com.emojiHW.service;

import com.emojiHW.domain.Emoji;
import com.emojiHW.domain.User;
import com.emojiHW.repository.EmojiRepository;
import com.emojiHW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmojiService {
    @Autowired
    public EmojiRepository emojiRepository;
    public Emoji findById(Long id){
        return emojiRepository.findById(id).get();
    }
}
