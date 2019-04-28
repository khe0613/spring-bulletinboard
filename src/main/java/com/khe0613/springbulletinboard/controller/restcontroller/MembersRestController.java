package com.khe0613.springbulletinboard.controller.restcontroller;

import com.google.gson.JsonObject;
import com.khe0613.springbulletinboard.dto.members.MembersInfoModifyRequestDto;
import com.khe0613.springbulletinboard.dto.members.MembersSignupRequestDto;
import com.khe0613.springbulletinboard.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class MembersRestController {
    private MembersService membersService;

    // 회원가입
    @PostMapping("/members/{id}")
    public String signUpMember(@RequestBody MembersSignupRequestDto dto, @PathVariable("id") String id){
        System.out.println("POST members/{id}");

        JsonObject obj= new JsonObject();

        if(membersService.getMember(id) == null){            // 동일한 id의 회원 존재
            membersService.signup(dto);

            obj.addProperty("result", "success");
            // json 형태로 리턴해야, client에서 success로 판단가능
            return obj.toString();
        }else{                                              // 동일한 id의 회원 미존재
            obj.addProperty("result", "fail");
            // json 형태로 리턴해야, client에서 success로 판단가능
            return obj.toString();
        }
    }

    // 회원정보 수정
    @PutMapping("/members/{id}")
    public String modifyMemberInfo(@RequestBody MembersInfoModifyRequestDto dto, @PathVariable("id") String id){
        System.out.println("PUT members/{id}");

        membersService.modifyMemberInfo(dto, id);

        JsonObject obj = new JsonObject();
        obj.addProperty("result", "success");
        return obj.toString();
    }

    // 회원 탈퇴
    @DeleteMapping("/members/{id}")
    public String leaveMember(@PathVariable("id") String id, HttpSession session){
        System.out.println("DELETE members/{id}");

        membersService.leaveMember(id);
        session.invalidate();
        JsonObject obj = new JsonObject();
        obj.addProperty("result", "success");
        return obj.toString();
    }


}
