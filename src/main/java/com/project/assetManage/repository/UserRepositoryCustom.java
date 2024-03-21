package com.project.assetManage.repository;

import com.project.assetManage.dto.UserDto;
import com.project.assetManage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryCustom {

    // 사용자 정보 (simple) 단건 조회
    UserDto.ResponseSimple selectUserSimple(UserDto.Request param);
    // 사용자 정보 (all) 단건 조회
    UserDto.ResponseAll selectUserAll(UserDto.Request param);
    // 사용자 정보와 해당 사용자의 소득 산출 정보 목록 조회
    List<UserDto.ResponseCustom> selectUserListWithComputeIncome(UserDto.Request param);


}
