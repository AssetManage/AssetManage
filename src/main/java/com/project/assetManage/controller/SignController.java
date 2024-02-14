package com.project.assetManage.controller;

import com.project.assetManage.dto.SignRequest;
import com.project.assetManage.dto.SignResponse;
import com.project.assetManage.dto.SignoutRequest;
import com.project.assetManage.service.SignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign")
public class SignController {

    private SignService signService;


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

}
