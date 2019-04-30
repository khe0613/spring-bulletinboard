package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.domain.posts.Posts;
import com.khe0613.springbulletinboard.dto.posts.PostsListResponseDto;
import com.khe0613.springbulletinboard.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        String userId = session.getAttribute("userId").toString();

        if(userId == null){                                     // 로그인 안되 있는 경우
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }else{                                                 // 로그인 되어 있는 경우
            modelAndView.setViewName("posts");
            modelAndView.addObject("post_list", postsService.findAllDesc());
            modelAndView.addObject("user_login", true);
            modelAndView.addObject("userId", userId);
            return modelAndView;
        }

    }
}
