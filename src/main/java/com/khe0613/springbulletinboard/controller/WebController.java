package com.khe0613.springbulletinboard.controller;

import com.khe0613.springbulletinboard.domain.member.Member;
import com.khe0613.springbulletinboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/posts")
    public String getPosts(Model model, HttpSession session){
        String userId = session.getAttribute("userId").toString();

        if(userId == null){     // 로그인 안되 있는 경우
        }else{                                                 // 로그인 되어 있는 경우
            model.addAttribute("user_login", true);
            model.addAttribute("userId", userId);
        }

        return "posts";
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

    // 로그인 기능, 로그인 기능은 Restful 하지 않음
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

        // 3분동안 아무런 요청이 없으면 세션 제거
        session.setMaxInactiveInterval(180);
        return modelAndView;
    }

    // 로그아웃 기능, 로그아웃 기능은 Restful 하지 않음
    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.removeAttribute("userId");
        return "redirect:/";
    }


    // 회원정보 조회
    @GetMapping("/members")
    public String getMember(HttpSession session){
        String id = session.getAttribute("userId").toString();

        // 현재 로그인되어 있는 계정의 정보를 조회
        return "redirect:members/" + id;
    }

    // 회원정보 조회
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable("id") String id, Model model, HttpSession session) {
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
