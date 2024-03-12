package com.project.assetManage.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.project.assetManage.dto.UserDto;
import com.project.assetManage.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "사용자 정보 조회")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@RequestMapping("st/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "사용자 정보 (simple) 단건 조회", description = "임시")
    @GetMapping("/selectUserSimple")
    public UserDto.ResultOne selectUserSimple(@Nullable @ModelAttribute UserDto.Request param, HttpSession session) {

        String stat = "SUCCESS";
        UserDto.ResponseSimple ret = userService.selectUserSimple(param);

        return new UserDto.ResultOne(stat, ret);
    }

    @Operation(summary = "사용자 정보와 해당 사용자의 소득 산출 정보 목록 조회", description = "임시")
    @GetMapping("/selectUserListWithComputeIncome")
    public UserDto.Result selectUserListWithComputeIncome(@Nullable @ModelAttribute UserDto.Request param, HttpSession session) {

        String stat = "SUCCESS";
        List<UserDto.ResponseCustom> list = userService.selectUserListWithComputeIncome(param);

        return new UserDto.Result(stat, list);
    }

}
