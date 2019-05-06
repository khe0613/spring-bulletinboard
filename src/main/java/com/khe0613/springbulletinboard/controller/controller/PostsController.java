package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.dto.posts.PostsDetailResponseDto;
import com.khe0613.springbulletinboard.service.MembersService;
import com.khe0613.springbulletinboard.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

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


    // 게시글 상세보기 기능
    @GetMapping("/posts/{post_number}")
    public ModelAndView getDetailPosts(@PathVariable("post_number") Long post_number, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        System.out.println("posts/{post_number}]");

        // 로그인 안되어 있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        String loginId = session.getAttribute("userId").toString();

        PostsDetailResponseDto target_post = postsService.getDeailtedPost(post_number);       // 상세보기 할 게시물
        String writerId = target_post.getId();                                             // 상세보기 할 게시물의 작성자

        if(loginId.equals(writerId)){     // 로그인한 사용자와 게시자가 같은 경우
            modelAndView.setViewName("detail.post");
            modelAndView.addObject("detailedPost", target_post);
            modelAndView.addObject("myPost", true);                                  // 본인이 작성한 게시물인 경우, 게시물 수정/삭제 버튼 표시
            modelAndView.addObject("user_login", true);                              // 우측 상단에 마이페이지, 로그아웃 버튼 표시
            modelAndView.addObject("userId", session.getAttribute("userId").toString());    // 우측 상단에 로그인한 아이디 표시
            return modelAndView;
        }else{                                   // 로그인한 사용자와 게시자가 다른 경우
            modelAndView.setViewName("detail.post");
            modelAndView.addObject("detailedPost", target_post);
            modelAndView.addObject("user_login", true);                              // 우측 상단에 마이페이지, 로그아웃 버튼 표시
            modelAndView.addObject("userId", session.getAttribute("userId").toString());    // 우측 상단에 로그인한 아이디 표시
            return modelAndView;
        }
    }


    // 게시글 검색 기능 (제목으로 검색)
    @GetMapping("/posts/title/{keyword}")
    public ModelAndView searchPostsByTitle(@PathVariable("keyword") String keyword,
                                           ModelAndView modelAndView,
                                           HttpSession session,
                                           RedirectAttributes redirectAttributes){

        System.out.println("GET /posts/title/{keyword}");

        // 로그인 안되어 있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        String loginId = session.getAttribute("userId").toString();

        modelAndView.setViewName("posts");
        modelAndView.addObject("post_list", postsService.findAllByTitleDesc(keyword));
        modelAndView.addObject("user_login", true);
        modelAndView.addObject("userId", session.getAttribute("userId").toString());

        return modelAndView;
    }


    // 게시글 검색 기능 (작성자로 검색)
    @GetMapping("/posts/writer/{keyword}")
    public ModelAndView searchPostByWriter(@PathVariable("keyword") String keyword,
                                           ModelAndView modelAndView,
                                           HttpSession session,
                                           RedirectAttributes redirectAttributes){
        System.out.println("GET /posts/writer/{keyword}");

        // 로그인 안되어 있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        String loginId = session.getAttribute("userId").toString();

        modelAndView.setViewName("posts");
        modelAndView.addObject("post_list", postsService.findAllByWriterDesc(keyword));
        modelAndView.addObject("user_login", true);
        modelAndView.addObject("userId", session.getAttribute("userId").toString());



        return modelAndView;
    }





}
