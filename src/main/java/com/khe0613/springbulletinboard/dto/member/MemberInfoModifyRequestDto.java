package com.khe0613.springbulletinboard.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberInfoModifyRequestDto {
    private String password;
    private String tel;
}
