package com.example.book.springboot.web;

import com.example.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외 다른 실행자를 실행시킴(여기선 SpringExtension 라는 스프링 실행자)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)// Web 테스트 어노테이션
public class HelloControllerTest {

    @Autowired // Bean 주입
    private MockMvc mvc; // 웹 API 테스트할때, 이 클래스로 GET, POST 등에 대한 테스트가 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc로 /hello 주소로 GET 요청을 보냄
                .andExpect(status().isOk()) // 위 mvc.perform 결과 검증, Header 값으루(200, 400 이런것들)
                .andExpect(content().string(hello)); // 위 mvc.perform 결과 검증, 응답 내용의 본문(컨트롤러에서 hello를 리턴하라고 했으니까 여기서도 hello인지 검증)
    }

    @Test
    public void helloDeto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name)
                                                  .param("amount", String.valueOf(amount)))  // API 테스트할때 사용할 파라미터 설정(숫자,날짜 등의 데이터도 문자열로 바꿔줘서 써야함)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.name",is(name)))   // JSON 응답값을 필드별로 검증 $ 기준으로 필드명 명시
                                .andExpect(jsonPath("$.amount", is(amount)));

    }



}
