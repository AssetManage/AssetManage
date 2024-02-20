package com.project.assetManage.service;

import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.ProductOption;
import com.project.assetManage.repository.ProductOptionRepository;
import com.project.assetManage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductOptionService {
    public ProductRepository productRepository;
    public ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductOptionService(ProductRepository productRepository, ProductOptionRepository productOptionRepository) {
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public List<ProductOptionDto> selectProductOptionList(Map<String, Object> param){
        List<ProductOptionDto> ret = productOptionRepository.selectProductOptionList(param).stream().collect(Collectors.toList());
        return ret;
    }

    public List<ProductOptionDto> selectProductOptionListSub(Map<String, Object> param){
        List<ProductOptionDto> ret = productOptionRepository.selectProductOptionListSub(param).stream().collect(Collectors.toList());
        return ret;
    }

}
