package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.UserAccountDto;

import java.util.List;

public interface UserAccountRepositoryCustom {
    // 회원이 보유한 예적금 상품 목록
    List<UserAccountDto.ResponseAll> selectUserAccountList(UserAccountDto.Request param);

    // 회원이 보유한 예적금 상품과 상품 정보 통합 목록
    List<UserAccountDto.ResponseCustom> selectUserAccountListWithProduct(UserAccountDto.Request param);

    // 내부 회원 중 특정 연령대가 보유한 예적금 상품 목록 :: 보유한 상품이 많은 순 우선 정렬
    List<ProductDto.ResponseSimple> selectProductListRcmdAgeCd(UserAccountDto.Request param);

    // 내부 회원 중 특정 소득범위가 보유한 예적금 상품 목록 :: 보유한 상품이 많은 순 우선 정렬
    List<ProductDto.ResponseSimple> selectProductListRcmdIncomeScopeCd(UserAccountDto.Request param);
}
