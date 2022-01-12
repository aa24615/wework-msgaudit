package com.php127.wework.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@EnableAutoConfiguration
public class LoginController {

    @RequestMapping("/login")
    public String login(Map<String,String> map){
        return "login";
    }

    @RequestMapping(path="/login",method = RequestMethod.POST)
    public String loginPost(Map<String,String> map){
        return "login";
    }
}
