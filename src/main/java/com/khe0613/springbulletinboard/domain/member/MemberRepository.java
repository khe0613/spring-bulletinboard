package com.khe0613.springbulletinboard.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
    void deleteById(String id);
}
