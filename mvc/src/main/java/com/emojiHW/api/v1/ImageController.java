package com.emojiHW.api.v1;

import com.amazonaws.services.s3.AmazonS3;
import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Image;
import com.emojiHW.domain.User;
import com.emojiHW.domain.Views;
import com.emojiHW.service.ImageService;
import com.emojiHW.service.StorageService;
import com.emojiHW.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@ResponseBody
@RequestMapping(value = {"/api/image", "/api/images"})
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    //url: /api/images POST image with default bucketName and key and connect with #2 user
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.POST)
    public Image uploadPicture(@RequestParam(value = "pic") MultipartFile picture ,@PathVariable("user_id") Long userId) {
        String bucketName = "emojihw-dev";
        String key = UUID.randomUUID().toString().replace("-","")+picture.getOriginalFilename();
//        if (null != picture && picture.getSize() > 0) {
//            for () {
//                String fileName = picture.getOriginalFilename();
//                fileName.add(fileName);
        File file = new File("/Users/Pinzhou/"+key);
        try {
            picture.transferTo(file);
            storageService.uploadObject(bucketName, file);
            Image image = new Image();
            image.setKey(key);
            image.setBucketName(bucketName);
            User sender = userService.findById(userId);
            image.setUser(sender);
            return imageService.save(image);

        } catch (IOException e) {
                logger.error("controller error: " + "System cannot find the picture");
        }
        logger.debug("Controller debug" + picture.getOriginalFilename());
        return null;
    }
//            }

    //GET image key by image Id, http://localhost:8080/api/image/8 to get id = 8
    @RequestMapping(method = RequestMethod.GET, value = "/{Id}")
    public Image getImageKeyById(@PathVariable("Id") Long Id) {
        Image imageKey = imageService.findKeyByImageId(Id);
        return imageKey;
    }

    //GET key by user_id  url: http://localhost:8080/api/images/user/1
    @RequestMapping(value="/user/{user_id}",method = RequestMethod.GET)
    public List<Image> getImageKey(@PathVariable("user_id") Long userId){
//        logger.debug("print out userId first "+userId);
//        Conversation conversation = conversationRepository.findByUserIdIgnoreCase(userId);
        List<Image> key = imageService.findImageKeyByUserId(userId);
        return key;
    }
}

