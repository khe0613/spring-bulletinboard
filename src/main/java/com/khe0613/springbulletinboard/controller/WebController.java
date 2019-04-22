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
}
