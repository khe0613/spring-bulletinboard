package com.khe0613.springbulletinboard.controller;

import com.khe0613.springbulletinboard.domain.member.Member;
import com.khe0613.springbulletinboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class WebController {
    @Autowired
    private MemberService memberService;

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

    // 로그인 기능은 Restful 하지 않음
    @PostMapping("/login")
    public ModelAndView doLogin(@RequestParam Map<String, String> login_info, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        Member member = memberService.getMember(login_info);

        // 해당 유저가 존재하지 않거나, 비밀번호가 틀렸을때
        if(member == null || !member.getPassword().equals(login_info.get("password"))){
            String login_message = "아이디 또는 비밀번호를 다시 확인하세요";

            // View를 "redirect:/" 로 지정하며, View로 보낼 Model 속성 값("login_message")를 설정해준다.
            // Redirect 되는 View에 대해 Model 속성 값을 넘겨주기 위해서는 조금 다른 코드가 필요하다.
            // 따라서, RedirectAttributes 객체와 이 객체의 addFlashAttribute() 메소드를 이용한다.
            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        // 로그인 성공
        modelAndView.setViewName("redirect:/weather");
        session.setAttribute("userId", member.getId());
        return modelAndView;
    }
}
