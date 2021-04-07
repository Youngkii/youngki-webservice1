package com.example.book.springboot.config.auth;

import com.example.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity   // SpringSecurity 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customUserTypesOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .headers().frameOptions().disable() // h2-console 화면 사용을 위해서 옵션 끄기
                .and()
                   .authorizeRequests() // URL별 권한 관리를 설정하는 옵션
                   .antMatchers("/", "/css/**", "/image/**", "/js/**", "/h2-console/**").permitAll() // 권한 관리 대상 지정하는 옵션 URL, HTTP 메소드별로 permitAll은 전체 열람 권한
                   .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // // 요거는 User 권한만 가능 /api/vi ~
                   .anyRequest().authenticated() // 설정된 값 이외의 것들 나머지 URL들은 로그인 한 사용자들만
                .and()
                   .logout() // 로그아웃 기능 로그아웃 성공시 해당 주소로 이동
                       .logoutSuccessUrl("/") // 여기가 그 해당주소
                .and()
                   .oauth2Login() // OAuth 2 로그인 기능 시작
                       .userInfoEndpoint() // 로그인 성공 이후의 사용자 정보 가져오는 설정
                           .userService(customUserTypesOAuth2UserService); // 로그인 성공 시 후속으로 사용할것 UserInterface 구현체 (리소스서버에서 정보 가져온걸로 가공하던가 하는것)

    }
}
