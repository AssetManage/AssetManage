package com.project.assetManage.dto;

import com.project.assetManage.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignResponse {

    private String email;
    private String token;

    @Builder
    public SignResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static SignResponse of(User user){
        return SignResponse.builder()
            .email(user.getEmail())
            .build();

    }
}
