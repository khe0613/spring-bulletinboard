package com.khe0613.springbulletinboard.domain.members;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_number;

    @Column(length = 20, nullable = false, unique = true)
    private String id;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String tel;

    @Builder
    public Members(String id, String password, String name, String tel) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.tel = tel;
    }


    // Entity 필드에 대한 값 변경이 필요한 경우,무분별한 setter 생성 보다는
    // 아래와 같이, 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가하는 것이 좋음
    public void modifyPassword(String new_password){
        this.password = new_password;
    }

    public void modifyTel(String new_tel){
        this.tel = new_tel;
    }
}
