package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.domain.posts.Posts;
import com.khe0613.springbulletinboard.dto.posts.PostsDetailResponseDto;
import com.khe0613.springbulletinboard.service.MembersService;
import com.khe0613.springbulletinboard.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class PostsController {
    PostsService postsService;
    MembersService membersService;

    // 게시판 게시물 목록 조회 기능
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

    /*
    // 게시글 상세보기 기능
    @GetMapping("/posts/{post_number}")
    public ModelAndView getDetailPosts(@PathVariable("post_number") Long post_number, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        // 로그인 안되어 있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        String loginId = session.getAttribute("userId").toString();

        PostsDetailResponseDto deailed_post = postsService.
        Posts target_post = postsService.getPost(post_number);      // 상세보기 할 게시물
        Members writer = target_post.getMember();                   // 상세보기 할 게시물의 작성자


        if(loginId.equals(writer.getId())){     // 로그인한 사용자와 게시자가 같은 경우
            modelAndView.setViewName("detail-post");
            modelAndView.addObject()
            return modelAndView;
        }else{                                   // 로그인한 사용자와 게시자가 다른 경우

            return modelAndView;
        }



        return null;
    }

     */
}
