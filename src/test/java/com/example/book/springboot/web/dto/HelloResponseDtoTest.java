package com.example.book.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {

        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);             // assertj 테스트 검증 라이브러리 메소드
        assertThat(dto.getAmount()).isEqualTo(amount);         // isEqualTo 는 저 두개 값 비교해서 맞으면 성공 반환

    }
}
