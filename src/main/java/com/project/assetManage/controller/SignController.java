package com.project.assetManage.controller;

import com.project.assetManage.dto.SignRequest;
import com.project.assetManage.dto.SignResponse;
import com.project.assetManage.dto.SignoutRequest;
import com.project.assetManage.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sign")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @Operation(summary = "회원가입", description = "신규 유저 회원가입")
    @PostMapping("/join")
    public SignResponse join(@RequestBody SignRequest request) {
        return signService.join(request);
    }

    @Operation(summary = "로그인", description = "기존 유저 로그인")
    @PostMapping("/login")
    public SignResponse login(@RequestBody SignRequest request){
        return signService.login(request);
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody SignoutRequest request){
        return signService.logout(request);
    }


    @Operation(summary = "이메일 중복체크", description = "이메일을 중복체크한다.")
    @GetMapping("/emailCheck")
    public ResponseEntity<Boolean> emailDuplicateCheck(@RequestParam("email") String email){
        return ResponseEntity.ok(signService.emailCheck(email));
    }

}
