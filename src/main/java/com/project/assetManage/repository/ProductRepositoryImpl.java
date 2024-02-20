package com.project.assetManage.repository;

import com.project.assetManage.entity.Product;
import com.project.assetManage.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> selectProductList(Map<String, Object> param) {
        System.out.println("selectProductList ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        // 임시
        QProduct product = QProduct.product;
        List<Product> list = jpaQueryFactory.select(product)
                .from(product)
                .where(product.actKindCd.eq(param.get("actKindCd").toString())
                        , product.joinWayCd.eq(param.get("joinWayCd").toString()))
                .fetch();
        return list;
    }
}
