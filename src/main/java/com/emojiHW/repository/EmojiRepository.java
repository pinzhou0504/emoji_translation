package com.emojiHW.repository;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import com.emojiHW.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface EmojiRepository extends CrudRepository<Emoji,Long> {
    List<Emoji> findAll();

    @Query("Select e FROM Emoji e join fetch e.conversation where e.code=?1")
    Emoji findEmojiByCodeIgnoreCase(String code);

    @Query("SELECT e from Emoji e join fetch e.conversation where e.conversation.id=?1")
    Emoji findEmojiByConversationId(Long conversationId);

//    @Query("SELECT c FROM Emoji c LEFT JOIN FETCH c.emoji where c.id = ?1")
//    Optional<Emoji> findByWithEmoji (Long id);
}
