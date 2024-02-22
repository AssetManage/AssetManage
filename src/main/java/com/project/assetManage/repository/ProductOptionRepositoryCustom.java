package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.ProductOption;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductOptionRepositoryCustom {

    // 소비유형에 따라 추출된 상품 옵션 PrdOptionSeq 목록의 정보 조회
    List<ProductOptionDto> selectProductOptionList(Map<String, Object> param);

    // 소비유형에 따라 save_trm 값 분기하여 목록 조회
    List<ProductOptionDto> selectProductOptionListSub(Map<String, Object> param);

}
