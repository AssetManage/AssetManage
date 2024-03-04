package com.project.assetManage.repository;

import com.project.assetManage.dto.CodeDto;
import com.project.assetManage.entity.common.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepositoryCustom {
    List<CodeDto.ResponseAll> selectCodeIdRegexpList(CodeDto.Request param);
}
