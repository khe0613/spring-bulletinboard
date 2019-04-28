package com.khe0613.springbulletinboard.dto.members;

import com.khe0613.springbulletinboard.domain.members.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembersSignupRequestDto {
    private String id;
    private String password;
    private String name;
    private String tel;

    public Members toEntity(){
        return Members.builder()
                .id(id)
                .password(password)
                .name(name)
                .tel(tel)
                .build();
    }

}
