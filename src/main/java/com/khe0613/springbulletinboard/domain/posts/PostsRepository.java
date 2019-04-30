package com.khe0613.springbulletinboard.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.post_number DESC ")
    Stream<Posts> findAllDesc();
}
