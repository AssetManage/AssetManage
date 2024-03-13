package com.project.assetManage.service;

import com.project.assetManage.dto.CodeDto;
import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.repository.CodeRepository;
import com.project.assetManage.repository.ProductOptionRepository;
import com.project.assetManage.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public ProductRepository productRepository;
    public ProductOptionRepository productOptionRepository;
    public CodeRepository codeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository
            , ProductOptionRepository productOptionRepository
            , CodeRepository codeRepository) {
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
        this.codeRepository = codeRepository;
    }
    
    // 상품 목록 조회
    public List<ProductDto.ResponseAll> selectProductList(ProductOptionDto.Request param){
        List<ProductDto.ResponseAll> ret = null;
        if(StringUtils.isEmpty(param.getCnsmpInclnCd())) param.setCnsmpInclnCd("AT");

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
        if(StringUtils.isEmpty(param.getCnsmpInclnCd())) param.setCnsmpInclnCd("AT");
        List<ProductOptionDto.ResponseSimple> prdOptList = productOptionRepository.selectProductOptionListSub(param);
        param.setPrdOptList(prdOptList);
        // limit 존재하는 경우, 반환 목록 갯수 제한
        if(0<param.getLimit()){
            ret = productRepository.selectProductListWithOptLimit(param);
        }else{
            ret = productRepository.selectProductListWithOpt(param);
        }

        // 상품 목록 div 내부 소비유형코드 아이콘 동적 생성을 위한 list 반환
        // TO-DO :: queryDsl 별도의 가공 필요, jpa regexp 사용시 오류 해결 방안
        /*
        * select *
          from st_code sc
         where grp_code_id = 'cnsmp_incln_cd'
           and code_id regexp 'BP|AT'
           and use_yn = 'Y'
        * */
        for (ProductDto.ResponseCustom product : ret) {
            CodeDto.Request code = new CodeDto.Request();
            code.setGroupCode("cnsmp_incln_cd");
            code.setUseYn('Y');
            code.setCodeIdListStr(product.getCnsmpInclnCdListStr());
            List<CodeDto.ResponseAll> codeList = codeRepository.selectCodeList(code);
            product.setCnsmpInclnCdList(codeList);
        }
        return ret;
    }
}
