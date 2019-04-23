package com.khe0613.springbulletinboard.dto.member;

import com.khe0613.springbulletinboard.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignupRequestDto {
    private String id;
    private String password;
    private String name;
    private String tel;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .tel(tel)
                .build();
    }

}
