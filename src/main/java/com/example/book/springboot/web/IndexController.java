package com.example.book.springboot.web;

import com.example.book.springboot.config.auth.LoginUser;
import com.example.book.springboot.config.auth.dto.SessionUser;
import com.example.book.springboot.service.posts.PostsService;
import com.example.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 템플릿에서 쓸 객체 저장(postsService.findALLDesc()) 의 결과를 posts 형식으로 전달
                                                                    // @LoginUser 사용하여 어느곳이던 세션 정보를 가져올수 있음

        model.addAttribute("posts", postsService.findAllDesc());

        // SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 로그인 성공 시 세션에 유저 저장
        
        if (user != null) {
            model.addAttribute("userName", user.getName()); // 세션에 저장된 값이 있으면 모델에 넣어둠(이러면 머스테치에서 이걸로 구분해서 로그인버튼 보이게 안보이게)
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";

    }

    // 테스트용
    @GetMapping("/secretPage")
    public String secret() {

        return "secretPage";
    }


}
