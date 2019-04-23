package com.khe0613.springbulletinboard.controller;

import com.google.gson.JsonObject;
import com.khe0613.springbulletinboard.dto.member.MemberSignupRequestDto;
import com.khe0613.springbulletinboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {
    private MemberService memberService;


    @PostMapping("/member")
    public String signupMember(@RequestBody MemberSignupRequestDto dto){
        memberService.signup(dto);

        JsonObject obj= new JsonObject();
        obj.addProperty("result", "success");

        // json 형태로 리턴해야, client에서 success로 판단가능
        return obj.toString();
    }
}
