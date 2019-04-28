package com.khe0613.springbulletinboard.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /*
    @Query("SELECT posts.post_number, posts.title, post.modified_date, members.id" +
            "FROM posts, members" +
            "ORDER BY posts.post_number DESC")
    Stream
     */
}
