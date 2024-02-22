package com.project.assetManage.service;

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

    public List<Product> selectProductList(Map<String, Object> param){
        List<Product> response = null;

        response = productRepository.selectProductList(param).stream()
                .map(m -> Product.builder()
                        .finCoNo(m.getFinCoNo())
                        .finPrdtCd(m.getFinPrdtCd())
                        .actKindCd(m.getActKindCd())
                        .dclsMonth(m.getDclsMonth())
                        .korCoNm(m.getKorCoNm())
                        .finPrdtNm(m.getFinPrdtNm())
                        .joinWay(m.getJoinWay())
                        .mtrtInt(m.getMtrtInt())
                        .spclCnd(m.getSpclCnd())
                        .joinDeny(m.getJoinDeny())
                        .joinMember(m.getJoinMember())
                        .etcNote(m.getEtcNote())
                        .maxLmit(m.getMaxLmit())
                        .dclsStrtDay(m.getDclsStrtDay())
                        .dclsEndDay(m.getDclsEndDay())
                        .joinWayCd(m.getJoinWayCd())
                        .build()
                ).collect(Collectors.toList());


        return response;
    }


}
