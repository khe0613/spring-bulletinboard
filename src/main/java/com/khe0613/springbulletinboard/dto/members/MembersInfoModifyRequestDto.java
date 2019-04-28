package com.khe0613.springbulletinboard.dto.members;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembersInfoModifyRequestDto {
    private String password;
    private String tel;
}
