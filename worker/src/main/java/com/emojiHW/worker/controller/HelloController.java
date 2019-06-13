package com.emojiHW.worker.controller;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public Map<String,String> healthCheck() {
        Map m = new HashMap<>();
        m.put("success","true");
        return m;
    }

}