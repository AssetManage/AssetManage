package com.project.assetManage.service;

import com.project.assetManage.dto.CodeDto;
import com.project.assetManage.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {
    public CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    // 코드 목록 조회
    public List<CodeDto.ResponseAll> selectProductList(CodeDto.Request param){
        List<CodeDto.ResponseAll> ret = codeRepository.selectCodeList(param);

        return ret;
    }
}
