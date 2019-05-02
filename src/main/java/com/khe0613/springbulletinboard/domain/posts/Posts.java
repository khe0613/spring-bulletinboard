package com.khe0613.springbulletinboard.domain.posts;

import com.khe0613.springbulletinboard.domain.BaseTimeEntity;
import com.khe0613.springbulletinboard.domain.members.Members;
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
    @Column(name="post_number")
    private Long postNumber;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer_number")
    private Members member;

    @Builder
    public Posts(String title, String content, Members member){
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
