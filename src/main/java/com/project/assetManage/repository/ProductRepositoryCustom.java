package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepositoryCustom {

    // 상품 목록 조회
    List<ProductDto.ResponseAll> selectProductList(ProductOptionDto.Request param);

    // 상품 목록 조회(limit)
    List<ProductDto.ResponseAll> selectProductListLimit(ProductOptionDto.Request param);

    // 소비유형별 상품 목록과 대표 옵션 동시 조회
    List<ProductDto.ResponseCustom> selectProductListWithOpt(ProductOptionDto.Request param);
    // 소비유형별 상품 목록과 대표 옵션 동시 조회(limit)
    List<ProductDto.ResponseCustom> selectProductListWithOptLimit(ProductOptionDto.Request param);

}
