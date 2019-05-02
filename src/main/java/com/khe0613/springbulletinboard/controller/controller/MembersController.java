package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class MembersController {
    @Autowired
    private MembersService membersService;

    // 회원정보 조회(마이페이지)
    @GetMapping("/members")
    public ModelAndView getMember(HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        System.out.println("GET members");

        // 로그인 안되있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        // 로그인 되어 있는 경우,
        // 현재 로그인되어 있는 계정의 정보를 조회
        modelAndView.setViewName("redirect:members/" + session.getAttribute("userId").toString());
        return modelAndView;
    }

    // 회원정보 조회(마이페이지)
    @GetMapping("/members/{id}")
    public ModelAndView getMember(@PathVariable("id") String id, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        System.out.println("GET members/{id}");

        // 로그인 안되있는 경우
        if(session.getAttribute("userId") == null){
            String login_message = "로그인이 필요한 서비스입니다.";

            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("login_message", login_message);
            return modelAndView;
        }

        String loginId = session.getAttribute("userId").toString();

        // 현재 로그인된 회원이 다른 회원의 마이페이지 조회를 요청할 경우
        // 요청이 그대로 수행되지 않고, 현재 로그인된 회원의 정보가 조회됨
        // id -> Request된 id
        // loginId -> 실제로 로그인 되어 있는 id
        if(!id.equals(loginId)){
            modelAndView.setViewName("redirect:/members/" + loginId);
            return modelAndView;
        }

        Members userInfo = membersService.getMember(id);
        modelAndView.setViewName("mypage");
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("user_login", true);
        modelAndView.addObject("userId", loginId);

        return modelAndView;
    }
}
