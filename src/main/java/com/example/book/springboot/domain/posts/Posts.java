package com.example.book.springboot.domain.posts;

import com.example.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스(기본적으로 소문자_ 테이블로 함)
public class Posts extends BaseTimeEntity {

    @Id  // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙  GenerationType.IDENTITY는 auto increment 설정임
    private Long id;

    @Column(length = 500, nullable = false)  // 선언 안해도 됨 테이블의 컬럼 나타냄 기본은 VARCHAR(255) 이나 긴 텍스트나 이런거 필요하면 여기서 설정함)
    private String title;

    @Column(columnDefinition = "TEXT" , nullable =  false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content) {

        this.title = title;
        this.content = content;

    }



}
