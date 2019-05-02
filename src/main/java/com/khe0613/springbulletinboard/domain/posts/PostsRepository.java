package com.khe0613.springbulletinboard.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.postNumber DESC ")
    Stream<Posts> findAllDesc();
    Posts findByPostNumber(Long post_number);

}
