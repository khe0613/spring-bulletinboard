package com.khe0613.springbulletinboard.domain.posts;

import com.khe0613.springbulletinboard.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_number;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long writer_number;

    @Builder
    public Posts(String title, String content, Long writer_number){
        this.title = title;
        this.content = content;
        this.writer_number = writer_number;
    }
}
