package com.khe0613.springbulletinboard.controller.restcontroller;

import com.google.gson.JsonObject;
import com.khe0613.springbulletinboard.dto.member.MemberSignupRequestDto;
import com.khe0613.springbulletinboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemberRestController {
    private MemberService memberService;

    // 회원가입!
    @PostMapping("/members/{id}")
    public String signUpMember(@RequestBody MemberSignupRequestDto dto, @PathVariable("id") String id){
        JsonObject obj= new JsonObject();

        if(memberService.getMember(id) == null){            // 동일한 id의 회원 존재
            memberService.signup(dto);

            obj.addProperty("result", "success");
            // json 형태로 리턴해야, client에서 success로 판단가능
            return obj.toString();
        }else{                                              // 동일한 id의 회원 미존재
            obj.addProperty("result", "fail");
            // json 형태로 리턴해야, client에서 success로 판단가능
            return obj.toString();
        }


    }
}
