package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.entity.QProduct;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductDto.ResponseAll> selectProductList(Map<String, Object> param) {
        System.out.println("selectProductList ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        // param
        String actKindCd = null;
        String joinWayCd = null;
        if(null != param.get("actKindCd")) actKindCd = param.get("actKindCd").toString();
        if(null != param.get("joinWayCd")) joinWayCd = param.get("joinWayCd").toString();

        // 메인 페이지 top3 여부
        boolean top3 = false;
        if(null != param.get("top3")) top3 = true;

        QProduct qProduct = QProduct.product;
        List<ProductDto.ResponseAll> list = null;
        // TO-DO :: OptionList 주입 -> all/recommand
        list = jpaQueryFactory.select(Projections.fields(ProductDto.ResponseAll.class
                        , qProduct.finCoNo
                        , qProduct.finPrdtCd
                        , qProduct.dclsMonth
                        , qProduct.actKindCd
                        , qProduct.korCoNm
                        , qProduct.finPrdtNm
                        , qProduct.joinWay
                        , qProduct.mtrtInt
                        , qProduct.spclCnd
                        , qProduct.joinDeny
                        , qProduct.joinMember
                        , qProduct.etcNote
                        , qProduct.maxLmit
                        , qProduct.dclsStrtDay
                        , qProduct.dclsEndDay
                        , qProduct.finCoSubmDay
                        , qProduct.joinWayCd
                        // custom
                        , new CaseBuilder()
                                .when(qProduct.joinWayCd.ne("F"))
                                .then(Expressions.constant("방문없이가입"))
                                .otherwise(Expressions.constant("")).as("joinWayElCnts")
                        , retCodeNm(qProduct.actKindCd, "act_kind_cd").as("actKindNm")
                        , retCodeNm(qProduct.joinWayCd, "join_way_cd").as("joinWayNm")
                ))
                .from(qProduct)
                .where(eqActKindCd(qProduct, actKindCd)
                        , eqJoinWayCd(qProduct, joinWayCd)
                )
                .orderBy(createOrderSpecifier(qProduct, "")) // TO-DO
                .fetch();
        return list;
    }

    // CUSTOM
    // 상품분류코드 Expression
    private BooleanExpression eqActKindCd(QProduct qProduct, String actKindCd) {
        if (StringUtils.isEmpty(actKindCd)) return null;
        return qProduct.actKindCd.eq(actKindCd);
    }
    // 가입방법코드 Expression
    private BooleanExpression eqJoinWayCd(QProduct qProduct, String joinWayCd) {
        if (StringUtils.isEmpty(joinWayCd)) return qProduct.joinWayCd.eq("A");
        return qProduct.joinWayCd.in(joinWayCd, "A");
    }
    // 공통코드명 Expression
    StringExpression retCodeNm(StringExpression column, String grpCodeId){
        return Expressions.stringTemplate("fn_get_code_nm({0}, {1})", grpCodeId, column);
    }
    // 소비유형에 따른 상품 목록 정렬
    // 선순위 :: 소비유형 , 후순위 :: 연령별, 소득별
    private OrderSpecifier createOrderSpecifier(QProduct qProduct, String cnsmpInclnCd) {
        return switch (cnsmpInclnCd) {
            /*
            case "GH" -> new OrderSpecifier<>(Order.DESC, qProduct.finCoNo);
            case "YL" -> new OrderSpecifier<>(Order.DESC, qProduct.finCoNo);
            case "BP" -> new OrderSpecifier<>(Order.DESC, qProduct.finCoNo);
            case "AT" -> new OrderSpecifier<>(Order.DESC, qProduct.finCoNo);
            case "SR" -> new OrderSpecifier<>(Order.DESC, qProduct.finCoNo);
             */
            // TO-DO :: 연령별

            // TO-DO :: 소득별
            default -> new OrderSpecifier<>(Order.ASC, qProduct.korCoNm);
        };
    }

}
