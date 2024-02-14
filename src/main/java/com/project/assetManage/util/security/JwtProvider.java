package com.project.assetManage.util.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    // 만료시간 : 30min
    private final long exp = 1000L * 60 * 30;

    private final CustomUserDetailService userDetailService;

    public JwtProvider(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostConstruct //bean이 초기화 됨과 동시에 의존성을 확인
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * token 생성 account : userId
     */
    public String createToken(String account, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(account); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
            .setClaims(claims) // 정보 저장
            .setIssuedAt(now)// 토큰 발행 시간 정보
            .setExpiration(new Date(now.getTime() + exp)) // set Expire Time
            .signWith(SignatureAlgorithm.HS256,
                secretKey) //사용할 암호화 알고리즘, signature 에 들어갈 secret 값 세팅
            .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
            userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    public String getAccount(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    //JWT 토큰의 남은 유효시간 확인
    public Long getExpiration(String accessToken){

        Date expiration = Jwts.parserBuilder().setSigningKey(secretKey.getBytes())
            .build().parseClaimsJws(accessToken).getBody().getExpiration();

        long now = new Date().getTime();
        return expiration.getTime() - now;
    }


}
