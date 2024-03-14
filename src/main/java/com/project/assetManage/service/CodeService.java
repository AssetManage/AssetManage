package com.project.assetManage.service;

import com.project.assetManage.dto.CodeDto;
import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.entity.common.ConsumptionInclination;
import com.project.assetManage.repository.CodeRepository;
import com.project.assetManage.repository.ConsumptionInclinationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {
    public CodeRepository codeRepository;
    public ConsumptionInclinationRepository consumptionInclinationRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository
        , ConsumptionInclinationRepository consumptionInclinationRepository) {
        this.codeRepository = codeRepository;
        this.consumptionInclinationRepository = consumptionInclinationRepository;
    }

    // 코드 목록 조회
    public List<CodeDto.ResponseAll> selectProductList(CodeDto.Request param){
        List<CodeDto.ResponseAll> ret = codeRepository.selectCodeList(param);

        // 소비성향코드인 경우, 추가 테이블 조회
        // TO-DO :: 따로 파일 빼서 조회하는 게 나은지 협의
        if(!StringUtils.isEmpty(param.getGroupCode())
                && param.getGroupCode().equals("cnsmp_incln_cd")){
            for (CodeDto.ResponseAll code : ret) {
                Object info = consumptionInclinationRepository.findAllByCnsmpInclnCd(code.getCodeId());
                code.setInfo(info);
            }
        }

        return ret;
    }
}
