package com.example.book.springboot.domain.posts;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.useDefaultRepresentation;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach  // Junit 에서 단위 테스트 끝날때마다 수행되는 메소드
    public void cleanup() {

        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title)               // 테이블 posts의 insert, update 쿼리 실행 있으면 insert 없으면 update
                                            .content(content)
                                            .author("이메일데스")
                                            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();            // 테이블 posts의 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

        useDefaultRepresentation();
    }

    @Test
    public void BaseTimeEntity_등록() {

        //given
        LocalDateTime now = LocalDateTime.of(1993,10,5,0,0,0);
        postsRepository.save(Posts.builder().title("시간자동제목").content("시간자동내용").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> 만든시각(CreateDate):" + posts.getCreatedDate() + "수정시각(ModifiedDate):" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now); // isAfter : 검증 대상의 시간이 인자로 전달된 시간 이후인지를 검증하는 메서드


    }





}
