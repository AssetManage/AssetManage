package com.project.assetManage.service;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.Product;
import com.project.assetManage.repository.ProductOptionRepository;
import com.project.assetManage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<ProductDto.ResponseAll> selectProductList(Map<String, Object> param){
        // inner join 옵션 list 조회 :: 임시
        List<ProductOptionDto.ResponseAll> selectprdOptionList = productOptionRepository.selectProductOptionList(param);
        param.put("prdOptionList", selectprdOptionList);

        List<ProductDto.ResponseAll> ret = productRepository.selectProductList(param);
        return ret;
    }


}
