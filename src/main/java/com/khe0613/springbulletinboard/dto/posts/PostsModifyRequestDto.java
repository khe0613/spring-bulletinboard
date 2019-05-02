package com.khe0613.springbulletinboard.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsModifyRequestDto {
    private String title;
    private String content;
}
