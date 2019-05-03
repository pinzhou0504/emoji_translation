package com.emojiHW.api.v1;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import com.emojiHW.domain.User;
import com.emojiHW.repository.EmojiRepository;
import com.emojiHW.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@ResponseBody
@RequestMapping(value = {"/api/emoji","/api/emojis"})
public class EmojiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmojiRepository emojiRepository;

    //url: /api/emoji POST emoji with short_name and code
    @RequestMapping(method = RequestMethod.POST)
    public Emoji addEmoji(@RequestBody Emoji emoji){
        emojiRepository.save(emoji);
        return emoji;
    }

    //url: /api/emojis GET emoji list
    @RequestMapping(method = RequestMethod.GET)
    public List<Emoji> getEmojiList(){
        logger.debug("list emojis");
        return emojiRepository.findAll();
    }

    //GET emoji by Id, http://localhost:8080/api/emojis/8 to get id = 8
    @RequestMapping(method = RequestMethod.GET,value = "/{Id}")
    public Emoji getEmojiById(@PathVariable("Id") Long Id){
        Optional<Emoji> opt = emojiRepository.findById(Id);
        return opt.get();
    }

    //GET /api/emojis?code=U+1F600
    @RequestMapping(method = RequestMethod.GET,params = {"code"})
    public Emoji getEmojiByCode(@RequestParam("code") String code){
        Emoji emoji = emojiRepository.findByCodeIgnoreCase(code);
        return emoji;
    }

    //url: /api/emoji DELETE http://localhost:8080/api/emojis/8 to delete id = 8
    @RequestMapping(method = RequestMethod.DELETE,value = "/{Id}")
    public void deleteEmoji(@PathVariable ("Id") Long Id){
//        User user = new User();
        emojiRepository.deleteById(Id);
    }


}
