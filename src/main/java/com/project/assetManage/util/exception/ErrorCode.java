package com.project.assetManage.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "LOGIN-001", "잘못된 이메일이거나 비밀번호입니다."),

    JOIN_FAIL(HttpStatus.BAD_REQUEST, "JOIN-001", "가입에 실패하였습니다."),
    JOIN_EMAIL_DUPLICATE(HttpStatus.INTERNAL_SERVER_ERROR, "JOIN-002", "이미 가입된 이메일입니다."),

    TOKEN_INVALID(HttpStatus.UNAUTHORIZED,"TOKEN-001", "유효하지 않은 토큰입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-001", "존재하지 않는 회원입니다."),

    // 필수 파라미터 체크
    USER_SEQ_IS_NULL(HttpStatus.BAD_REQUEST, "PARAM-001", "사용자 SEQ는 필수 파라미터입니다."),
    AGE_CD_IS_NULL(HttpStatus.BAD_REQUEST, "PARAM-002", "연령대는 필수 파라미터입니다."),
    INCOME_SCOPE_CD_IS_NULL(HttpStatus.BAD_REQUEST, "PARAM-003", "소득범위코드는 필수 파라미터입니다."),

    DUPLICATE_TELNUM(HttpStatus.BAD_REQUEST, "USER-002", "이미 가입된 번호입니다.")

    ;


    private final HttpStatus httpStatus;
    private final String code;				// error code
    private final String message;			// description

}
