package com.project.assetManage.repository;

import com.project.assetManage.entity.common.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, String> {

    Optional<Code> findByGroupCodeAndCodeId(String groupCode, String codeId);

    // 그룹코드에 해당하는 코드 목록 조회
    List<Code> findByGroupCodeAndUseYn(String groupCode, Character useYn);
}
