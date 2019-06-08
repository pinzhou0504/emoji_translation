package com.emojiHW.api.v1;

import com.emojiHW.domain.Views;
import com.emojiHW.extend.security.JwtTokenUtil;
import com.emojiHW.extend.security.RestAuthenticationRequest;
import com.emojiHW.domain.User;
import com.emojiHW.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@ResponseBody
@RequestMapping(value = {"/api/users", "/api/user"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private Views views;




    //url: /api/users POST
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        User u = userService.createUser(user);
//        u.setAuthorities("ROLE_REGISTERED_USER");
        return u;
    }

    //url: /api/users/login POST
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody RestAuthenticationRequest restAuthenticationRequest){
        logger.debug(restAuthenticationRequest.getUsername());
        logger.debug(restAuthenticationRequest.getPassword());
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(
                    restAuthenticationRequest.getUsername(),
                    restAuthenticationRequest.getPassword()
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
            //在springsecuritycontect里面钻个洞
            SecurityContextHolder.getContext().setAuthentication(authentication);
            try{
                final UserDetails userDetails = userService.findByUsernameIgnoreCase(restAuthenticationRequest.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                //把token转换成json格式在postman里
                Map<String,String> tokenToString = new HashMap<>();
                tokenToString.put("token:",token);
                ResponseEntity<?> responseEntity = ResponseEntity.ok(tokenToString);
                return responseEntity;
            } catch (NotFoundException|NullPointerException e){
                logger.error("System can't find user by email or username",e);
                return ResponseEntity.notFound().build();
            }

        } catch (AuthenticationException ex){
//            logger.error("authentication failure, please check your username/password");
            Map m = new HashMap<>();
            m.put("error:","authentication failure, please check your username/password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
        }
    }



    //url: /api/user GET user list
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.UserConversationViews.class)
    public List<User> getUserList() {
        logger.debug("list users");
        return userService.findAll();
    }

    //GET user by Id, http://localhost:8080/api/users/8 to get id = 8
    @RequestMapping(method = RequestMethod.GET, value = "/{Id}")
    @JsonView(Views.UserConversationViews.class)
    public User getUserById(@PathVariable("Id") Long Id) {
        User user = userService.findById(Id);
        return user;
    }

    //GET /api/users?username=SSmith
    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    @JsonView(Views.UserConversationViews.class)
    public User getUserByUsername(@RequestParam("username") String username) {
        User user = userService.findByUsernameIgnoreCaseWithConversation(username);
        return user;
    }

    //url: /api/user DELETE http://localhost:8080/api/users/8 to delete id = 8
    @RequestMapping(method = RequestMethod.DELETE, value = "/{Id}")
    @JsonView(Views.UserConversationViews.class)
    public void deleteUser(@PathVariable("Id") Long Id) {
//        User user = new User();
        userService.deleteById(Id);
    }

    //url: /api/users?phoneNumber=1234567890 GET user by phone number
    @RequestMapping(method = RequestMethod.GET,params = {"phoneNumber"})
    @JsonView(Views.UserViews.class)
    public List<User> getUserByPhoneNumber(@RequestParam("phoneNumber")String phoneNumber){
        List<User> user = userService.findByPhoneNumber(phoneNumber);
        return user;
    }



}
