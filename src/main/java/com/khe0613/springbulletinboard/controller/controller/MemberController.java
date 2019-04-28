package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.domain.member.Member;
import com.khe0613.springbulletinboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    // 회원정보 조회(마이페이지)
    @GetMapping("/members")
    public String getMember(HttpSession session){
        System.out.println("GET members");

        String id = session.getAttribute("userId").toString();

        // 현재 로그인되어 있는 계정의 정보를 조회
        return "redirect:members/" + id;
    }

    // 회원정보 조회(마이페이지)
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable("id") String id, Model model, HttpSession session) {
        System.out.println("GET members/{id}");

        String loginId = session.getAttribute("userId").toString();

        // 현재 로그인된 회원이 다른 회원의 마이페이지 조회를 요청할 경우
        // 요청이 그대로 수행되지 않고, 현재 로그인된 회원의 정보가 조회됨
        // id -> Request된 id
        // loginId -> 실제로 로그인 되어 있는 id
        if(!id.equals(loginId)){
            return "redirect:/members/" + loginId;
        }

        Member member = memberService.getMember(id);
        model.addAttribute("userInfo", member);
        return "mypage";
    }
}
