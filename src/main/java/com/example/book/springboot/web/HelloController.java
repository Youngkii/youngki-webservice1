package com.example.book.springboot.web;

import com.example.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어줌
public class HelloController {

    @GetMapping("/hello") // HTTP Method 인 Get의 요청을 받을 수 있는 Api를 만들어줌(옛날 @RequsetMapping)
                          // /hello 로 요청이 올시 hello 리턴
    public String hello() {

        return  "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,     // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
                                     @RequestParam("amount") int amount ){  // 여기서는 외부에서 name(@RequestParam("name")) 으로 넘긴 파라미터를 메소드 name(String name) 에 저장

        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/bye")
    public String bye() {

        return  "바이바이";
    }
}
