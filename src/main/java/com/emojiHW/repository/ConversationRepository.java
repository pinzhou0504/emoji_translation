package com.emojiHW.repository;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation,Long> {
    List<Conversation>findAll();
    @Query("Select c FROM Conversation c LEFT JOIN FETCH c.user where c.id = ?1 ")
    List<Conversation> findAllWithConversation();

}
