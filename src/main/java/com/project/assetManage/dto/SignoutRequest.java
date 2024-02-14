package com.project.assetManage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignoutRequest {

    private String email;
    private String token;

    @Builder
    public SignoutRequest(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
