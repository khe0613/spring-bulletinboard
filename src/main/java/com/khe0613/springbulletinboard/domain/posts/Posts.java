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

    // Entity 필드에 대한 값 변경이 필요한 경우,무분별한 setter 생성 보다는
    // 아래와 같이, 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가하는 것이 좋음
    public void modifyTitle(String title){
        this.title = title;
    }

    public void modifyContent(String content){
        this.content = content;
    }
}
