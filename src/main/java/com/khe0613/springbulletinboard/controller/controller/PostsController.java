package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;

    // 게시판 게시물 목록 조회 기닁
    @GetMapping("/posts")
    public ModelAndView getPosts(HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        // 로그인 안되있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        // 로그인 되어 있는 경우

        modelAndView.setViewName("posts");
        modelAndView.addObject("post_list", postsService.findAllDesc());
        modelAndView.addObject("user_login", true);
        modelAndView.addObject("userId", session.getAttribute("userId").toString());
        return modelAndView;


    }
}
