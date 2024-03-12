package com.project.assetManage.repository;

import com.project.assetManage.dto.UserAccountDto;

import java.util.List;

public interface UserAccountRepositoryCustom {
    List<UserAccountDto.ResponseAll> selectUserAccountList(UserAccountDto.Request param);

    List<UserAccountDto.ResponseCustom> selectUserAccountListWithProduct(UserAccountDto.Request param);
}
