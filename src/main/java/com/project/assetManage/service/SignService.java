package com.project.assetManage.service;

import com.project.assetManage.dto.SignRequest;
import com.project.assetManage.dto.SignResponse;
import com.project.assetManage.dto.SignoutRequest;
import com.project.assetManage.entity.User;
import com.project.assetManage.repository.UserRepository;
import com.project.assetManage.util.exception.CustomException;
import com.project.assetManage.util.exception.ErrorCode;
import com.project.assetManage.util.security.JwtProvider;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignService(UserRepository userRepository, PasswordEncoder passwordEncoder,
        JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public SignResponse join(SignRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new CustomException(ErrorCode.JOIN_EMAIL_DUPLICATE);
        }
        try{
            String password = passwordEncoder.encode(request.getPassword());
            User user = request.toUserEntity(password, request);

            userRepository.save(user);
        }catch (Exception e) {
            throw new CustomException(ErrorCode.JOIN_FAIL);
        }

        User joinedUser = userRepository.findUserByEmail(request.getEmail())
            .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        return SignResponse.of(joinedUser);
    }

    @Transactional
    public SignResponse login(SignRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
            .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_FAIL));

        if (!passwordEncoder.matches(request.getPassword(), user.getLoginPw())) {
            throw new CustomException(ErrorCode.LOGIN_FAIL);
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
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }

        Authentication authentication = jwtProvider.getAuthentication(request.getToken());
        return authentication.isAuthenticated();
    }

    @Transactional(readOnly = true)
    public boolean emailCheck(String email) {
        return userRepository.existsByEmail(email);
    }
}
