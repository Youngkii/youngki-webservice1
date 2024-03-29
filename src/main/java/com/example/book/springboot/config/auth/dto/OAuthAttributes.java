package com.example.book.springboot.config.auth.dto;

import com.example.book.springboot.domain.user.Role;
import com.example.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User 반환하는 사용자 정보가 Map임
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){

        // 네이버에서 들어왔을 시 추가
        if("naver".equals(registrationId)){

            return  ofNaver("id",attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);

    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // User 엔티티 생성
    public User toEntity() {

        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)  // 유저 생성시 기본 유저권한
                .build();

    }


}
