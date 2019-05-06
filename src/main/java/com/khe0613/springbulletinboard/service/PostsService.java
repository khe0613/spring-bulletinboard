package com.khe0613.springbulletinboard.service;

import com.khe0613.springbulletinboard.domain.posts.Posts;
import com.khe0613.springbulletinboard.domain.posts.PostsRepository;
import com.khe0613.springbulletinboard.dto.posts.PostsDetailResponseDto;
import com.khe0613.springbulletinboard.dto.posts.PostsListResponseDto;
import com.khe0613.springbulletinboard.dto.posts.PostsModifyRequestDto;
import com.khe0613.springbulletinboard.dto.posts.PostsRegisterRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    // (readOnly = true) 옵션을 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어
    // 조회 속도가 개선됨. 따라서 CREATE/UPDATE/DELETE 기능이 없는 메소드라면 사용하는게 좋음
    // 전체 게시물 목록을 post_number에 대한 내림차순으로 조회 하는 기능
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시물 리스트 검색 (글 제목으로 검색)
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllByTitleDesc(String title){
        return postsRepository.findAllByTitleDesc(title)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    /*
     * 게시물 리스트 검색(작성자로 검색)
     * Stream의 filter를 이용한 방법
     *
     * @Transactional(readOnly = true)
     * public List<PostsListResponseDto> findAllByWriterDesc(String writer){
     *      return postsRepository.findAllDesc()
     *           .map(PostsListResponseDto::new)
     *           .filter(postsListResponseDto -> postsListResponseDto.getId().equals(writer))
     *           .collect(Collectors.toList());
     * }
    */

    // 게시물 리스트 검색(작성자로 검색)
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllByWriterDesc(String writer){
        return postsRepository.findAllByWriterDesc(writer)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }




    // 게시물 등록
    @Transactional
    public void postRegister(PostsRegisterRequestDto dto){
        postsRepository.save(dto.toEntity());
    }


    // 게시글 상세 내용 조회(반환)
    @Transactional(readOnly = true)
    public PostsDetailResponseDto getDeailtedPost(Long post_number){
        PostsDetailResponseDto detailed_post = postsRepository.findDetailedPostByPostNumber(post_number);
        return detailed_post;
    }

    // 게시글 수정
    @Transactional
    public void postModify(Long post_number, PostsModifyRequestDto dto){
        Posts post = postsRepository.findByPostNumber(post_number);
        post.modifyTitle(dto.getTitle());
        post.modifyContent(dto.getContent());
    }

    // 게시글 삭제
    @Transactional
    public void postDelete(Long post_number){
        postsRepository.deleteByPostNumber(post_number);
    }


}
