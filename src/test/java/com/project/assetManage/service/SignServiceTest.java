package com.project.assetManage.service;

import static org.junit.jupiter.api.Assertions.*;

import com.project.assetManage.controller.SignController;
import com.project.assetManage.dto.SignRequest;
import com.project.assetManage.dto.SignResponse;
import com.project.assetManage.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignServiceTest {

    @Autowired
    private SignService signService;

    @Autowired
    private SignController signController;

    @Test
    void login(){

        SignRequest request = SignRequest.builder()
            .email("test@email.com")
            .password("password")
            .build();

        SignResponse response = signService.login(request);
        System.out.println(response.getToken());

//        System.out.println(signController.test());

    }

}