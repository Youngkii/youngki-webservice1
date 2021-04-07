package com.example.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언한 모든 필드의 get 메소드 생성
@RequiredArgsConstructor // 선언한 모든 필드의 생성자 생성(final 붙은거만)
public class HelloResponseDto {

    private final String name;
    private final int amount;


}
