package com.emojiHW.api.v1;

import com.emojiHW.domain.Conversation;
import com.emojiHW.domain.User;
import com.emojiHW.repository.ConversationRepository;
import com.emojiHW.repository.UserRepository;
import com.emojiHW.service.ConversationService;
import com.emojiHW.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/conversations","/api/conversation"})
public class ConversationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserService userService ;

    // url: /api/conversations/user/2 POST conversations with user_id = 2
    @RequestMapping(value="/user/{user_id}",method = RequestMethod.POST)
    public Conversation addContent(@RequestBody Conversation conversation,@PathVariable("user_id") Long userId){

        User sender = userService.findById(userId);
        conversation.setUser(sender);
        conversationRepository.save(conversation);

        return conversation;
    }



//    //url: /api/conversation GET conversation list
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Conversation> getConversationList(){
//        logger.debug("list conversations");
//        return conversationRepository.findAll();
//    }

    //GET conversation by user_id  url: http://localhost:8080/api/conversations/user/1
    @RequestMapping(value="/user/{user_id}",method = RequestMethod.GET)
    public Conversation getConversation(@PathVariable("user_id") Long userId){
//        logger.debug("print out userId first "+userId);
//        Conversation conversation = conversationRepository.findByUserIdIgnoreCase(userId);
        Conversation c = new Conversation();
        return new Conversation();
    }

    //GET content by id: /api/conversations/8
    @RequestMapping(method = RequestMethod.GET,value = "/{Id}")
    public Conversation getContentById(@PathVariable("Id") Long Id){
        Optional<Conversation> opt = conversationRepository.findById(Id);
        return opt.get();
    }

    //url: /api/conversations DELETE http://localhost:8080/api/conversations/8 to delete id = 8
    @RequestMapping(method = RequestMethod.DELETE,value = "/{Id}")
    public void deleteContent(@PathVariable ("Id") Long Id){
        conversationRepository.deleteById(Id);
    }
}
