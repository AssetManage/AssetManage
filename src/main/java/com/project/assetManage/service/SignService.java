package com.project.assetManage.service;

import com.project.assetManage.dto.SignRequest;
import com.project.assetManage.dto.SignResponse;
import com.project.assetManage.dto.SignoutRequest;
import com.project.assetManage.entity.User;
import com.project.assetManage.repository.UserRepository;
import com.project.assetManage.util.security.JwtProvider;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignService(UserRepository userRepository, PasswordEncoder passwordEncoder,
        JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public SignResponse join(SignRequest request) {
        try{
            if (userRepository.existsByEmail(request.getEmail())){
                throw new EntityExistsException("이미 가입된 이메일입니다.");
            }

            String password = passwordEncoder.encode(request.getPassword());
            User user = request.toUserEntity(password, request);

            userRepository.save(user);
        }catch (Exception e) {
            throw new IllegalStateException("가입에 실패하였습니다.");
        }

        User joinedUser = userRepository.findUserByEmail(request.getEmail())
            .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 회원입니다."));

        return SignResponse.of(joinedUser);
    }

    @Transactional
    public SignResponse login(SignRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
            .orElseThrow(() -> new EntityNotFoundException("잘못된 이메일 혹은 비밀번호입니다"));

        if (passwordEncoder.matches(request.getPassword(), passwordEncoder.encode(user.getLoginPw()))) {
            throw new IllegalStateException("잘못된 이메일 혹은 비밀번호입니다");
        }

        SignResponse result = SignResponse.of(user);

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        result.setToken(jwtProvider.createToken(user.getEmail(), roles));

        return result;
    }

    @Transactional
    public boolean logout(SignoutRequest request){
        if(!jwtProvider.validateToken(request.getToken())){
            throw new IllegalArgumentException("로그아웃 : 유효하지 않은 토큰입니다.");
        }

        Authentication authentication = jwtProvider.getAuthentication(request.getToken());
        return authentication.isAuthenticated();
    }

}
