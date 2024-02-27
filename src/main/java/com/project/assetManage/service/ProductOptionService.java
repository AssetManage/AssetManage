package com.project.assetManage.service;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
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

    public List<ProductOptionDto.ResponseAll> selectProductOptionList(ProductOptionDto.Request param){
        List<ProductOptionDto.ResponseAll> ret = productOptionRepository.selectProductOptionList(param).stream().collect(Collectors.toList());
        return ret;
    }

    public ProductOptionDto.ResponseSimple selectProductOption(ProductOptionDto.Request param){
        ProductOptionDto.ResponseSimple ret = productOptionRepository.selectProductOption(param);
        return ret;
    }

    public List<ProductOptionDto.ResponseSimple> selectProductOptionListSub(ProductOptionDto.Request param){
        List<ProductOptionDto.ResponseSimple> ret = productOptionRepository.selectProductOptionListSub(param);
        return ret;
    }

}
