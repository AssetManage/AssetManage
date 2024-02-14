package com.project.assetManage.util.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtProvider jwtProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(JwtProvider jwtProvider,
        JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.jwtProvider = jwtProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정
        return (web) -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean  // 비밀번호 암호화
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)// 쿠키 기반이 아닌 JWT 기반이므로 사용하지 않음
            .sessionManagement((sessionManagement) ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
            )
            // 조건별로 요청 허용/제한 설정
            .authorizeHttpRequests((authorizeRequests)-> {
                // 회원가입과 로그인은 모두 승인
                authorizeRequests.requestMatchers("/join", "/login").permitAll();
                authorizeRequests.requestMatchers("/user/**").authenticated();
                authorizeRequests.anyRequest().permitAll();
            })
            .formLogin((formLogin) -> {
                /* 권한이 필요한 요청은 해당 url로 리다이렉트 */
                formLogin.loginPage("/login");
            })
            // JWT 인증 필터 적용
            .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter.class)
             // 에러 핸들링
            .exceptionHandling(
                exception -> exception.accessDeniedHandler(jwtAccessDeniedHandler)
                    .authenticationEntryPoint((request, response, authException) -> {
                        // 인증문제가 발생했을 때 이 부분을 호출한다.
                        response.setStatus(401);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("인증되지 않은 사용자입니다.");
                    })
            );

        return http.build();
    }

}
