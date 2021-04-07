package com.example.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성 될 위치
@Retention(RetentionPolicy.RUNTIME) // 요건 커스텀 어노테이션의 메모리 범위 설정해주는거
public @interface LoginUser {




}
