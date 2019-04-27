package com.khe0613.springbulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String getMain(){
        return "main";
    }

    @GetMapping("/signup")
    public String getSignIn(){
        return "signup";
    }

    @GetMapping("/weather")
    public String getWeater(){
        return "weather";
    }

    @GetMapping("/posts")
    public String getPosts(){
        return "posts";
    }

    @GetMapping("/chatting")
    public String getChatting(){
        return "chatting";
    }
}
