package com.emojiHW.api.v1;

import com.emojiHW.extend.security.RestAuthenticationRequest;
import com.emojiHW.domain.User;
import com.emojiHW.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@ResponseBody
@RequestMapping(value = {"/api/users", "/api/user"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    //url: /api/users POST
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        User u = userService.createUser(user);
        return u;
    }

    //url: /api/users/login POST
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RestAuthenticationRequest login(@RequestBody RestAuthenticationRequest restAuthenticationRequest){
        logger.debug(restAuthenticationRequest.getUsername());
        logger.debug(restAuthenticationRequest.getPassword());
        return restAuthenticationRequest;
    }



    //url: /api/user GET user list
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.debug("list users");
        return userService.findAll();
    }

    //GET user by Id, http://localhost:8080/api/users/8 to get id = 8
    @RequestMapping(method = RequestMethod.GET, value = "/{Id}")
    public User getUserById(@PathVariable("Id") Long Id) {
        User user = userService.findById(Id);
        return user;
    }

    //GET /api/users?username=SSmith
    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public User getUserByUsername(@RequestParam("username") String username) {
        User user = userService.findByUsernameIgnoreCase(username);
        return user;
    }

    //url: /api/user DELETE http://localhost:8080/api/users/8 to delete id = 8
    @RequestMapping(method = RequestMethod.DELETE, value = "/{Id}")
    public void deleteUser(@PathVariable("Id") Long Id) {
//        User user = new User();
        userService.deleteById(Id);
    }
}
