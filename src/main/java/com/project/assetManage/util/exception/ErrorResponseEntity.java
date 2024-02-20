package com.project.assetManage.util.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class ErrorResponseEntity {
    private int status;
    private String name;
    private String code;
    private String message;

    @Builder
    public ErrorResponseEntity(int status, String name, String code, String message) {
        this.status = status;
        this.name = name;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e){
        return ResponseEntity
            .status(e.getHttpStatus())
            .body(ErrorResponseEntity.builder()
                .status(e.getHttpStatus().value())
                .name(e.name())
                .code(e.getCode())
                .message(e.getMessage())
                .build());
    }
}