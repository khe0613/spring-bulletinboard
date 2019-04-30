package com.khe0613.springbulletinboard.controller.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @GetMapping("/")
    public String getMain(HttpSession session){
        // 만약 로그인 되어있는 상태인 경우, '날씨' 페이지로 Redirect
        if(session.getAttribute("userId") != null){
            return "redirect:/weather";
        }

        return "main";
    }

    @GetMapping("/signup")
    public String getSignIn(){
        return "signup";
    }

    @GetMapping("/weather")
    public String getWeater(Model model, HttpSession session){
        String userId = session.getAttribute("userId").toString();

        if(userId == null){     // 로그인 안되 있는 경우
        }else{                                                 // 로그인 되어 있는 경우
            model.addAttribute("user_login", true);
            model.addAttribute("userId", userId);
        }

        return "weather";
    }


    @GetMapping("/chatting")
    public String getChatting(Model model, HttpSession session){
        String userId = session.getAttribute("userId").toString();

        if(userId == null){     // 로그인 안되 있는 경우
        }else{                                                 // 로그인 되어 있는 경우
            model.addAttribute("user_login", true);
            model.addAttribute("userId", userId);
        }
        return "chatting";
    }





}
