package com.project.assetManage.controller;

import com.project.assetManage.dto.SignRequest;
import com.project.assetManage.dto.SignResponse;
import com.project.assetManage.dto.SignoutRequest;
import com.project.assetManage.service.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sign")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/join")
    public SignResponse join(@RequestBody SignRequest request) {
        return signService.join(request);
    }

    @PostMapping("/login")
    public SignResponse login(@RequestBody SignRequest request){
        return signService.login(request);
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody SignoutRequest request){
        return signService.logout(request);
    }


    @GetMapping("/emailCheck/{email}")
    public ResponseEntity<Boolean> emailDuplicateCheck(@PathVariable String email){
        return ResponseEntity.ok(signService.emailCheck(email));
    }

}
