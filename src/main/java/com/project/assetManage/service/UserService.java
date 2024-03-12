package com.project.assetManage.service;

import com.project.assetManage.dto.UserAccountDto;
import com.project.assetManage.dto.UserDto;
import com.project.assetManage.repository.UserAccountRepository;
import com.project.assetManage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserRepository userRepository;
    public UserAccountRepository userAccountRepository;

    @Autowired
    public UserService(UserRepository userRepository
            , UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public UserDto.ResponseSimple selectUserSimple(UserDto.Request param){
        UserDto.ResponseSimple ret = userRepository.selectUserSimple(param);
        return ret;
    }

    public UserDto.ResponseAll selectUserAll(UserDto.Request param){
        UserDto.ResponseAll ret = userRepository.selectUserAll(param);
        return ret;
    }
    public List<UserDto.ResponseCustom> selectUserListWithComputeIncome(UserDto.Request param){
        List<UserDto.ResponseCustom> ret = userRepository.selectUserListWithComputeIncome(param);

        // 사용자의 계좌/카드 목록 조회
        for (UserDto.ResponseCustom user : ret) {
            UserAccountDto.Request account = new UserAccountDto.Request();
            account.setUserSeq(user.getUserSeq());
            List<UserAccountDto.ResponseAll> accountList = userAccountRepository.selectUserAccountList(account);
            // 보유 상품 정보 통합 조회시 사용
            // List<UserAccountDto.ResponseCustom> accountList = userAccountRepository.selectUserAccountListWithProduct(account);
            user.setUserAccountList(accountList);
        }
        return ret;
    }
}
