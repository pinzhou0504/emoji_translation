package com.emojiHW.repository;
import com.emojiHW.domain.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image,Long> {

    @Query("SELECT k from Image k join fetch k.user where k.user.id=?1")
    List<Image> findImageKeyByUserId(Long userId);
}
