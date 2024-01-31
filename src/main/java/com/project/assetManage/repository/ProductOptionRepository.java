package com.project.assetManage.repository;

import com.project.assetManage.entity.ProductOption;
import com.project.assetManage.entity.ProductOptionKeyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, ProductOptionKeyId> {

}
