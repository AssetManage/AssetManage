package com.project.assetManage.service;

import com.project.assetManage.dto.UserAccountDto;
import com.project.assetManage.dto.UserDto;
import com.project.assetManage.entity.User;
import com.project.assetManage.repository.UserAccountRepository;
import com.project.assetManage.repository.UserRepository;
import com.project.assetManage.util.exception.CustomException;
import com.project.assetManage.util.exception.ErrorCode;
import com.project.assetManage.util.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;
    public final UserAccountRepository userAccountRepository;

    public UserService(UserRepository userRepository
            , UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
    }


    public UserDto.ResponseSimple selectUserSimple(UserDto.Request param) {
        UserDto.ResponseSimple ret = userRepository.selectUserSimple(param);
        return ret;
    }

    public UserDto.ResponseAll selectUserAll(UserDto.Request param) {
        UserDto.ResponseAll ret = userRepository.selectUserAll(param);
        return ret;
    }

    public List<UserDto.ResponseCustom> selectUserListWithComputeIncome(UserDto.Request param) {
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

    @Transactional(readOnly = true)
    public UserDto.UserMyInfoResponseDTO getUserMyInfo(UserDto.Request param) {
        UserDto.ResponseAll allInfo= userRepository.selectUserAll(param);

        UserDto.UserMyInfoResponseDTO ret = UserDto.UserMyInfoResponseDTO
                .builder()
                .userSeq(allInfo.getUserSeq())
                .userNm(allInfo.getUserNm())
                .email(allInfo.getEmail())
                .profileImgUrl(allInfo.getProfileImgUrl())
                .sexCd(allInfo.getSexCd())
                .age(allInfo.getAge())
                .occupationCd(allInfo.getOccupationCd())
                .mobileTelNum(allInfo.getMobileTelNum())
                .zipCd(allInfo.getZipCd())
                .zipDetailAddr1(allInfo.getZipDetailAddr1())
                .zipDetailAddr2(allInfo.getZipDetailAddr2())
                .prdtRcmdItemCd(allInfo.getPrdtRcmdItemCd())
                .cnsmpInclnCd(allInfo.getCnsmpInclnCd())
                .cnsmpInclnNm(allInfo.getCnsmpInclnNm())
                .build();
        return ret;
    }

    @Transactional
    public UserDto.UpdateDTO updateUserInfo(CustomUserDetails user, UserDto.Request request) {

        User userUpdate = userRepository.findById(user.getUser().getUserSeq())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //전화번호 중복체크
        List<User> userList = userRepository.findAllByMobileTelNum(request.getMobileTelNum());
        if(!userList.isEmpty()){
            for(User u : userList){
                String telNum = u.getMobileTelNum();
                long seq = u.getUserSeq();
                if(telNum.equals(request.getMobileTelNum()) && seq != user.getUser().getUserSeq()){
                    throw  new CustomException(ErrorCode.DUPLICATE_TELNUM);
                }
            }
        }

        userUpdate.updateInfo(request);

        return new UserDto.UpdateDTO(userUpdate);
    }
}

