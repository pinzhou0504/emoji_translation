package com.emojiHW.repository;

import com.emojiHW.domain.Emoji;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface EmojiRepository extends CrudRepository<Emoji,Long> {
    List<Emoji> findAll();
    @Query("Select e FROM Emoji e ")
    List<Emoji> findEmoji();

//    @Query("SELECT c FROM Emoji c LEFT JOIN FETCH c.emoji where c.id = ?1")
//    Optional<Emoji> findByWithEmoji (Long id);
}
