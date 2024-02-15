package com.project.assetManage.repository;

import com.project.assetManage.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAccountDetailRepository extends JpaRepository<AccountDetail, String> {
}
