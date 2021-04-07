package com.example.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 사용 (WebMvcTest 때 스캔하는거 막으려구 여기에 생성)
public class JpaConfig {
}
