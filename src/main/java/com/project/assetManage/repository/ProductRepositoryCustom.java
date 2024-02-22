package com.project.assetManage.repository;

import com.project.assetManage.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepositoryCustom {

    List<Product> selectProductList(Map<String, Object> param);


}
