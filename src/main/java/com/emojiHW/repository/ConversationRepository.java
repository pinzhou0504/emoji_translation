package com.emojiHW.repository;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation,Long> {
    List<Conversation>findAll();
    @Query("Select c FROM Conversation c")
    List<Conversation> findConversation();

}
