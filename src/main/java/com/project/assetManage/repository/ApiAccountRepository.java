package com.project.assetManage.repository;

import com.project.assetManage.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiAccountRepository  extends JpaRepository<UserAccount, String> {
}

