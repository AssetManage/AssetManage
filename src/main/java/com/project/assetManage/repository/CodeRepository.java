package com.project.assetManage.repository;

import com.project.assetManage.entity.common.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, String> {

    Optional<Code> findByGroupCodeAndCodeId(String groupCode, String codeId);

}
