package com.project.assetManage.repository;

import com.project.assetManage.entity.UserComuteIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputeIncomeRepository extends JpaRepository<UserComuteIncome, String> {

}

