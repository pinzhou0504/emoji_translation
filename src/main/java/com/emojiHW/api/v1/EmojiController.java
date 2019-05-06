package com.emojiHW.api.v1;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.Emoji;
import com.emojiHW.domain.User;
import com.emojiHW.repository.EmojiRepository;
import com.emojiHW.repository.UserRepository;
import com.emojiHW.service.ConversationService;
import com.emojiHW.service.EmojiService;
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
    private EmojiService emojiService;
    @Autowired
    private  ConversationService conversationService;

    //url: /api/emoji/conversation/2 POST emoji with short_name and code and connect with #2 conversation
    @RequestMapping(value="/conversation/{conversation_id}",method = RequestMethod.POST)
    public Emoji addEmoji(@RequestBody Emoji emoji,@PathVariable("conversation_id") Long conversationId){


        Conversation sender = conversationService.findById(conversationId);
        emoji.setConversation(sender);
        emojiService.save(emoji);

        return emoji;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public Emoji addEmoji(@RequestBody Emoji emoji){
//        emojiRepository.save(emoji);
//        return emoji;
//    }

//    //url: /api/emojis GET emoji list
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Emoji> getEmojiList(){
//        logger.debug("list emojis");
//        return emojiRepository.findAll();
//    }

    //GET emoji by Id, http://localhost:8080/api/emojis/8 to get id = 8
    @RequestMapping(method = RequestMethod.GET,value = "/{Id}")
    public Emoji getEmojiById(@PathVariable("Id") Long Id){
        Optional<Emoji> opt = emojiService.findById(Id);
        return opt.get();
    }

    //GET /api/emojis?code=U%2B1F600
    @RequestMapping(method = RequestMethod.GET,params = {"code"})
    public Emoji getEmojiByCode(@RequestParam("code") String code){
        Emoji emoji = emojiService.findByCodeIgnoreCase(code);
        return emoji;
    }

    //url: /api/emoji DELETE http://localhost:8080/api/emojis/8 to delete id = 8
    @RequestMapping(method = RequestMethod.DELETE,value = "/{Id}")
    public void deleteEmoji(@PathVariable ("Id") Long Id){
//        User user = new User();
        emojiService.deleteById(Id);
    }


}
