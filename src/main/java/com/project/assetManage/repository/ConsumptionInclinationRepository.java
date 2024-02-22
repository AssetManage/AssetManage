package com.project.assetManage.repository;

import com.project.assetManage.entity.common.ConsumptionInclination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumptionInclinationRepository extends JpaRepository<ConsumptionInclination, String> {

    Optional<ConsumptionInclination> findByOverCnsmpIncln(Double overCnsmpIncln);

}




