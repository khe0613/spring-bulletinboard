package com.khe0613.springbulletinboard.controller.controller;

import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

// 로그인 & 로그아웃 에 대한 컨트롤러
@Controller
public class LoginController {
    @Autowired
    private MembersService membersService;

    // 로그인 기능, 로그인 기능은 Restful 하지 않음
    @PostMapping("/login")
    public ModelAndView doLogin(@RequestParam Map<String, String> login_info, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        Members member = membersService.getMember(login_info);

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
       // session.setMaxInactiveInterval(180);          효율적인 개발을 위해 잠시 주석처리
        return modelAndView;
    }

    // 로그아웃 기능, 로그아웃 기능은 Restful 하지 않음
    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
