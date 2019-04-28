package com.khe0613.springbulletinboard.domain.members;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MembersRepositoryTest {

    @Autowired
    MembersRepository membersRepository;


    @After
    public void cleanup(){
        /*
        이후 테스트 코드에 영향을 끼치지 않기 위해
        테스트 메소드가 끝날때 마다 repository 전체를 비우는 코드
         */
        membersRepository.deleteAll();
    }

    @Test
    public void 회원저장_불러오기(){
        //given
        membersRepository.save(Members.builder()
                    .id("khe0616")
                    .password("changheekim")
                    .name("홍길동")
                    .tel("010-1111-1111")
                    .build());

        //when
        List<Members> memberList = membersRepository.findAll();

        //then
        Members member = memberList.get(0);
        assertThat(member.getId(), is("khe0616"));
        assertThat(member.getPassword(), is("changheekim"));
        assertThat(member.getName(), is("홍길동"));
        assertThat(member.getTel(), is("010-1111-1111"));
    }

}