package com.khe0613.springbulletinboard.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// Entity들의 생성, 수정시간 자동관리
@Getter
@MappedSuperclass                               // JPA Entity들이 BaseTimeEntity를 상속할 경우 필드들도 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
public class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime modified_date;

}
