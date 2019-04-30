package com.khe0613.springbulletinboard.dto.posts;

import com.khe0613.springbulletinboard.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

// Controller에서 @RequestBody로 외부 데이터를 받는 경우,
// 기본 생성자 + setter 메소드를 통해서만 값이 할당 되어짐
@Getter
@Setter
@NoArgsConstructor
public class PostsListResponseDto {
    private Long post_number;
    private String title;
    private String id;
    private String modified_date;       // View 영역에서는 LocalDateTime 타입을 모르기 때문에
                                        // String 타입으로 변경하여 response 해준다.

    public PostsListResponseDto(Posts entity){
        this.post_number = entity.getPost_number();
        this.title = entity.getTitle();
        this.id = entity.getMember().getId();
        this.modified_date = toStringDateTime(entity.getModified_date());
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
