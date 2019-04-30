package com.khe0613.springbulletinboard.dto.posts;

import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsRegisterRequestDto {
    private String title;
    private String content;
    private Members writer;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .member(writer)
                .build();
    }
}
