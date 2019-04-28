package com.khe0613.springbulletinboard.domain.members;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {
    Members findById(String id);
    void deleteById(String id);
}
