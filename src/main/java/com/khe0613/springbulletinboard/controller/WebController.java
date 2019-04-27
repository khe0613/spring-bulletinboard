package com.khe0613.springbulletinboard.controller;

import com.khe0613.springbulletinboard.domain.member.Member;
import com.khe0613.springbulletinboard.dto.LoginRequestDto;
import com.khe0613.springbulletinboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

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
    public String doLogin(@RequestBody LoginRequestDto dto, HttpSession session, Model model){

        Member member = memberService.getMember(dto);

        // 해당 유저가 존재하지 않거나, 비밀번호가 틀렸을때
        if(dto == null || member.getPassword().equals(dto.getPassword()) == false){
            model.addAttribute("login_message", "아이디 또는 비밀번호를 다시 확인하세요");
            return "redirect:/";
        }

        // 로그인 성공
        session.setAttribute("userId", member.getId());
        return "/weather";
    }
}
