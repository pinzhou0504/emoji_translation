package com.emojiHW.api.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/image", "/api/images"})
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //url: /api/images POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, String> uploadPicture(@RequestParam(value = "pic")MultipartFile picture){
        logger.debug("controller logger: "+picture.getOriginalFilename());
        return new HashMap<>();
    }

}
