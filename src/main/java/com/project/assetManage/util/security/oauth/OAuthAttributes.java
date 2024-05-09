package com.project.assetManage.util.security.oauth;


import com.project.assetManage.entity.StringArrayConverter;
import com.project.assetManage.entity.User;
import com.project.assetManage.util.security.oauth.socialUserInfo.KakaoOAuth2UserInfo;
import com.project.assetManage.util.security.oauth.socialUserInfo.NaverOAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

//소셜에서 가져온 유저 정보를 담을 DTO
@Getter
public class OAuthAttributes {

    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드
    private OAuth2UserInfo oAuth2UserInfo; // 소셜 타입별 로그인 유저 정보

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuthAttributes of(String socialType, String userNameAttributeName, Map<String, Object> attributes){
        if(socialType.equals("kakao")){
            return ofKakao(userNameAttributeName,attributes);
        }
        return ofNaver(userNameAttributeName,attributes);
    }


    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    public User toEntity(OAuth2UserInfo oAuth2UserInfo){
        return User.builder()
                .email(oAuth2UserInfo.getEmail() == null ? oAuth2UserInfo.getId() + "@test.com" : oAuth2UserInfo.getEmail())
                .userNm(oAuth2UserInfo.getNickname())
                .loginId(oAuth2UserInfo.getId())
                .roles(new StringArrayConverter().convertToEntityAttribute("ROLE_GUEST"))
                .build();
    }

}
