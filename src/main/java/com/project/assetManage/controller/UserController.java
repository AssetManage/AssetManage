package com.project.assetManage.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.project.assetManage.dto.UserDto;
import com.project.assetManage.service.UserService;
import com.project.assetManage.util.exception.CustomException;
import com.project.assetManage.util.exception.ErrorCode;
import com.project.assetManage.util.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "사용자 정보 조회")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "사용자 정보 (simple) 단건 조회", description = "내부 회원의 사용자 일부 정보를 단건 조회한다.")
    @GetMapping("/selectUserSimple")
    public UserDto.ResultOne selectUserSimple(@Nullable @ModelAttribute UserDto.Request param
            , @AuthenticationPrincipal CustomUserDetails user) {

        String stat = "SUCCESS";

        if(!ObjectUtils.isEmpty(user)) param.setUserSeq(user.getUser().getUserSeq());
        if(ObjectUtils.isEmpty(param.getUserSeq())) throw new CustomException(ErrorCode.USER_SEQ_IS_NULL);

        UserDto.ResponseSimple ret = userService.selectUserSimple(param);

        return new UserDto.ResultOne(stat, ret);
    }

    @Operation(summary = "사용자 정보 (all) 단건 조회", description = "내부 회원의 사용자 전체 정보를 단건 조회한다.")
    @GetMapping("/selectUserAll")
    public UserDto.ResultOne selectUserAll(@Nullable @ModelAttribute UserDto.Request param
            , @AuthenticationPrincipal CustomUserDetails user) {

        String stat = "SUCCESS";

        if(!ObjectUtils.isEmpty(user)) param.setUserSeq(user.getUser().getUserSeq());
        if(ObjectUtils.isEmpty(param.getUserSeq())) throw new CustomException(ErrorCode.USER_SEQ_IS_NULL);

        UserDto.ResponseAll ret = userService.selectUserAll(param);

        return new UserDto.ResultOne(stat, ret);
    }

    @Operation(summary = "사용자 정보와 해당 사용자의 소득 산출 정보 목록 조회", description = "내부 회원의 사용자 정보와 소득 산출 정보를 통합한 목록을 조회한다.")
    @GetMapping("/selectUserListWithComputeIncome")
    public UserDto.Result selectUserListWithComputeIncome(@Nullable @ModelAttribute UserDto.Request param
            , @AuthenticationPrincipal CustomUserDetails user) {

        String stat = "SUCCESS";

        List<UserDto.ResponseCustom> list = userService.selectUserListWithComputeIncome(param);

        return new UserDto.Result(stat, list);
    }

    @Operation(summary = "마이페이지 정보 조회", description = "해당 유저의 마이페이지 정보를 조회한다.")
    @GetMapping("/myInfo")
    public UserDto.ResultOne getMyInfo( @AuthenticationPrincipal CustomUserDetails user){
        UserDto.Request param = new UserDto.Request(user.getUser().getUserSeq());
        UserDto.UserMyInfoResponseDTO ret = userService.getUserMyInfo(param);
        String stat = "SUCCESS";
        return new UserDto.ResultOne(stat, ret);
    }


    @PostMapping("/update")
    public UserDto.ResultOne updateUserInfo(@AuthenticationPrincipal CustomUserDetails user, @RequestBody UserDto.Request request){
        UserDto.UpdateDTO ret = userService.updateUserInfo(user, request);
        String stat = "SUCCESS";
        return new UserDto.ResultOne(stat, ret);
    }

}
