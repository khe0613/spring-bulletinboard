package com.khe0613.springbulletinboard.controller.restcontroller;

import com.google.gson.JsonObject;
import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.dto.posts.PostsRegisterRequestDto;
import com.khe0613.springbulletinboard.service.MembersService;
import com.khe0613.springbulletinboard.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class PostsRestController {
    private PostsService postsService;
    private MembersService membersService;

    // 게시물 등록
    @PostMapping("/posts")
    public String postRegister(@RequestBody PostsRegisterRequestDto dto, HttpSession session){
        System.out.println("POST /posts");
        Members writer = membersService.getMember(session.getAttribute("userId").toString());
        dto.setWriter(writer);

        postsService.postRegister(dto);

        JsonObject obj = new JsonObject();
        obj.addProperty("result", "success");
        return obj.toString();
    }
}
