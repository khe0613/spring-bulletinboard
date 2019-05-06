package com.khe0613.springbulletinboard.domain.posts;

import com.khe0613.springbulletinboard.dto.posts.PostsDetailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.postNumber DESC ")
    Stream<Posts> findAllDesc();
    PostsDetailResponseDto findDetailedPostByPostNumber(Long post_number);
    Posts findByPostNumber(Long post_number);
    void deleteByPostNumber(Long post_number);


    @Query("SELECT p " +
            "FROM Posts p " +
            "WHERE p.title like concat('%', :searchTitle, '%') " +
            "ORDER BY p.postNumber DESC")
    Stream<Posts> findAllByTitleDesc(@Param("searchTitle") String searchTitle);


}

