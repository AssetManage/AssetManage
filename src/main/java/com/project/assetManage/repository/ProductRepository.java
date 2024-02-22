package com.project.assetManage.repository;

import com.project.assetManage.entity.Product;
import com.project.assetManage.entity.ProductKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductKeyId>, ProductRepositoryCustom {
}
