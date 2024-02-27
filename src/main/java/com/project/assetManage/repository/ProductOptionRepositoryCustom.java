package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepositoryCustom {

    // 상품 옵션 목록 조회
    // return List<ProductOptionDto.ResponseAll>
    List<ProductOptionDto.ResponseAll> selectProductOptionList(ProductOptionDto.Request param);

    // 소비유형코드에 해당하는 상품 옵션 순번 단건 조회
    // return ProductOptionDto.ResponseSimple
   ProductOptionDto.ResponseSimple selectProductOption(ProductOptionDto.Request param);

    // 상품별 소비유형코드에 해당하는 상품 옵션 순번 목록 조회
    // return List<ProductOptionDto.ResponseSimple>
    List<ProductOptionDto.ResponseSimple> selectProductOptionListSub(ProductOptionDto.Request param);
}
