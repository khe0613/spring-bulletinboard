package com.khe0613.springbulletinboard.domain.posts;

import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.domain.members.MembersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    MembersRepository membersRepository;        // BaseTimeEntity 테스트할때 사용됨(posts 테이블이 members 테이블 참조하므로)

    @After
    public void cleanup(){
        /*
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        */
        postsRepository.deleteAll();
    }

    @Test
    public void BaseTimeEntity_등록(){
        // given
        membersRepository.save(Members.builder()
                .id("abc123")
                .password("pwdabc")
                .name("테스트")
                .tel("010-1234-5678")
                .build());

        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시물")
                .content("하이")
                .writer_number((long)1)
                .build());

        // when
        List<Posts> postsList =  postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreated_date().isAfter(now));
        assertTrue(posts.getModified_date().isAfter(now));
    }
}