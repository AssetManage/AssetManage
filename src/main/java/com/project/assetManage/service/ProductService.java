package com.project.assetManage.service;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.repository.ProductOptionRepository;
import com.project.assetManage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    public ProductRepository productRepository;
    public ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductOptionRepository productOptionRepository) {
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
    }
    
    // 상품 목록 조회
    public List<ProductDto.ResponseAll> selectProductList(ProductOptionDto.Request param){
        List<ProductDto.ResponseAll> ret = null;

        // limit 존재하는 경우, 반환 목록 갯수 제한
        if(0<param.getLimit()){
            ret = productRepository.selectProductListLimit(param);
        }else{
            ret = productRepository.selectProductList(param);
        }

        // 상품의 상품 옵션 목록 조회
        for (ProductDto.ResponseAll product : ret) {
            param.setFinCoNo(product.getFinCoNo());
            param.setFinPrdtCd(product.getFinPrdtCd());
            param.setDclsMonth(product.getDclsMonth());

            List<ProductOptionDto.ResponseAll> productOptionList = productOptionRepository.selectProductOptionList(param);
            product.setProductOptionList(productOptionList);
        }

        return ret;
    }

    // 소비유형별 상품 목록과 대표 옵션 동시 조회
    public List<ProductDto.ResponseCustom> selectProductListWithOpt(ProductOptionDto.Request param){
        List<ProductDto.ResponseCustom> ret = null;
        // 특정 상품 옵션 순번 목록 조회
        List<ProductOptionDto.ResponseSimple> prdOptList = productOptionRepository.selectProductOptionListSub(param);
        param.setPrdOptList(prdOptList);
        // limit 존재하는 경우, 반환 목록 갯수 제한
        if(0<param.getLimit()){
            ret = productRepository.selectProductListWithOptLimit(param);
        }else{
            ret = productRepository.selectProductListWithOpt(param);
        }
        return ret;
    }
}
