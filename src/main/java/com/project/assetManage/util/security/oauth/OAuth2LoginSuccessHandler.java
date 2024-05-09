package com.project.assetManage.util.security.oauth;

import com.project.assetManage.entity.User;
import com.project.assetManage.util.security.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, ServletException {
        log.info("OAuth2 Login 성공!");

        CustomOAuthUser oAuth2User = (CustomOAuthUser) authentication.getPrincipal();

        // 최초 로그인 = ROLE_GUEST
        if(oAuth2User.getRole().equals("ROLE_GUEST")){
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_GUEST");

            User user = User.builder()
                    .email(oAuth2User.getEmail())
                    .userNm(oAuth2User.getName())
                    .loginId(oAuth2User.getNickName())
                    .roles(roles)
                    .build();

            String token = JwtProvider.createToken(user.getLoginId(), roles);
            response.addHeader(JwtProvider.HEADER, token); //accesstoken only!
            request.getRequestDispatcher("/test/oauth/join").forward(request, response);
        }
        else{
            loginSuccess(request, response, oAuth2User);
        }

    }

    private void loginSuccess(HttpServletRequest request, HttpServletResponse response, CustomOAuthUser oAuth2User) throws ServletException, IOException {

        List<String> roles = new ArrayList<>();
        roles.add(oAuth2User.getRole()); //ROLE_USER

        User user = User.builder()
                .email(oAuth2User.getEmail())
                .userNm(oAuth2User.getName())
                .loginId(oAuth2User.getNickName())
                .roles(roles)
                .build();

        String accessToken = JwtProvider.createToken(user.getLoginId(), roles);
        String refreshToken = JwtProvider.createRefreshToken(user.getLoginId(), roles);

        response.addHeader(JwtProvider.HEADER, accessToken);
//        response.addHeader(JwtTokenProvider.HEADER, refreshToken);

        ResponseCookie cookie = ResponseCookie.from("refresh-token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")      // path
                .maxAge(JwtProvider.REFRESH_EXP)
                .sameSite("None")  // sameSite
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        request.getRequestDispatcher("/test/oauth/login").forward(request, response);
    }
}
