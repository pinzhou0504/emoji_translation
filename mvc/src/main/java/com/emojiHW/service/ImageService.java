package com.emojiHW.service;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Image;
import com.emojiHW.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Image findKeyByImageId(Long id) {
        return imageRepository.findById(id).get();
    }

    @Transactional
    public List<Image> findImageKeyByUserId(Long userId) {
        return imageRepository.findImageKeyByUserId(userId);
    }
}
