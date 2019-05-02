package com.khe0613.springbulletinboard.dto.posts;

import com.khe0613.springbulletinboard.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class PostsDetailResponseDto {
    private Long post_number;
    private String title;
    private String content;
    private String id;
    private String modified_date;       // View 영역에서는 LocalDateTime 타입을 모르기 때문에
                                        // String 타입으로 변경하여 response 해준다.

    public PostsDetailResponseDto(Posts entity){
        this.post_number = entity.getPostNumber();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.id = entity.getMember().getId();
        this.modified_date = toStringDateTime(entity.getModifiedDate());
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
