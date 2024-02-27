package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.QProduct;
import com.project.assetManage.entity.QProductOption;
import com.project.assetManage.util.expression.CodeExpression;
import com.project.assetManage.util.expression.ProductExpression;
import com.project.assetManage.util.expression.ProductOptionExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // 상품 목록 조회
    // fetchOne : 상품의 옵션 목록 갯수 제한(전체 목록/소비유형별 대표 옵션 단건)
    @Override
    public List<ProductDto.ResponseAll> selectProductList(ProductOptionDto.Request param) {
        System.out.println("selectProductList ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        QProduct qProduct = QProduct.product;
        List<ProductDto.ResponseAll> list = null;

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
                        , CodeExpression.retCodeNm(qProduct.actKindCd, "act_kind_cd").as("actKindNm")
                        , CodeExpression.retCodeNm(qProduct.joinWayCd, "join_way_cd").as("joinWayNm")
                ))
                .from(qProduct)
                .where(
                        ProductExpression.eqFinCoNo(qProduct, param.getFinCoNo())
                        , ProductExpression.eqFinPrdtCd(qProduct, param.getFinPrdtCd())
                        , ProductExpression.eqDclsMonth(qProduct, param.getDclsMonth())
                        , ProductExpression.eqActKindCd(qProduct, param.getActKindCd())
                        , ProductExpression.eqJoinWayCd(qProduct, param.getJoinWayCd())
                        , ProductExpression.inFinCoNoList(qProduct, param.getFinCoNoList())
                )
                .fetch();
        return list;
    }

    // 소비유형별 상품 목록과 대표 옵션 동시 조회
    @Override
    public List<ProductDto.ResponseCustom> selectProductListWithOpt(ProductOptionDto.Request param) {
        System.out.println("selectProductListWithOpt ====================================");
        System.out.println(param.toString());

        QProductOption qProductOption = QProductOption.productOption;

        // 임시
        List<ProductOptionDto.ResponseSimple> optList = param.getPrdOptList();
        QProduct qProduct = QProduct.product;

        List<ProductDto.ResponseCustom> list = null;
        list = jpaQueryFactory.select(Projections.fields(ProductDto.ResponseCustom.class
                        , qProductOption.prdOptionSeq
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth
                        , qProductOption.intrRateType
                        , qProductOption.intrRateTypeNm
                        , qProductOption.saveTrm
                        , qProductOption.intrRate
                        , qProductOption.intrRate2
                        , qProductOption.rsrvType
                        , qProductOption.rsrvTypeNm
                        // custom
                        , ProductOptionExpression.retMaturityAmt(qProductOption).as("maturityAmt")

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
                        , Expressions.as(Expressions.constant(ProductExpression.retCnsmpInclnCdList(param.getCnsmpInclnCd())), "cnsmpInclnCdList")
                        , CodeExpression.retCodeNm(qProduct.actKindCd, "act_kind_cd").as("actKindNm")
                        , CodeExpression.retCodeNm(qProduct.joinWayCd, "join_way_cd").as("joinWayNm")
                ))
                .from(qProductOption)
                .innerJoin(qProduct)
                .on(qProduct.dclsMonth.eq(qProductOption.dclsMonth)
                        , qProduct.finCoNo.eq(qProductOption.finCoNo)
                        , qProduct.finPrdtCd.eq(qProductOption.finPrdtCd))
                .where(
                        ProductExpression.eqFinCoNo(qProduct, param.getFinCoNo())
                        , ProductExpression.eqFinPrdtCd(qProduct, param.getFinPrdtCd())
                        , ProductExpression.eqDclsMonth(qProduct, param.getDclsMonth())
                        , ProductExpression.eqJoinWayCd(qProduct, param.getJoinWayCd())
                        , ProductExpression.eqActKindCd(qProduct, param.getActKindCd())
                        , ProductExpression.inFinCoNoList(qProduct, param.getFinCoNoList())
                        , Expressions.list(qProductOption.prdOptionSeq
                                , qProductOption.finCoNo
                                , qProductOption.finPrdtCd
                                , qProductOption.dclsMonth).in(ProductOptionExpression.inPrdOptionList(optList))
                )
                .orderBy(ProductOptionExpression.orderSpecifiers(qProductOption, param.getCnsmpInclnCd(), qProduct.actKindCd))
                .fetch();
        return list;
    }
    // TO-DO :: 중복되는 조회 쿼리 코드 모듈화
    // 상품 목록 조회(limit)
    @Override
    public List<ProductDto.ResponseAll> selectProductListLimit(ProductOptionDto.Request param) {
        System.out.println("selectProductList ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        QProduct qProduct = QProduct.product;
        List<ProductDto.ResponseAll> list = null;

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
                        , CodeExpression.retCodeNm(qProduct.actKindCd, "act_kind_cd").as("actKindNm")
                        , CodeExpression.retCodeNm(qProduct.joinWayCd, "join_way_cd").as("joinWayNm")
                ))
                .from(qProduct)
                .where(
                        ProductExpression.eqFinCoNo(qProduct, param.getFinCoNo())
                        , ProductExpression.eqFinPrdtCd(qProduct, param.getFinPrdtCd())
                        , ProductExpression.eqDclsMonth(qProduct, param.getDclsMonth())
                        , ProductExpression.eqActKindCd(qProduct, param.getActKindCd())
                        , ProductExpression.eqJoinWayCd(qProduct, param.getJoinWayCd())
                        , ProductExpression.inFinCoNoList(qProduct, param.getFinCoNoList())
                )
                .limit(param.getLimit())
                .fetch();
        return list;
    }
    // 소비유형별 상품 목록과 대표 옵션 동시 조회(limit)
    @Override
    public List<ProductDto.ResponseCustom> selectProductListWithOptLimit(ProductOptionDto.Request param) {
        System.out.println("selectProductListWithOpt ====================================");
        System.out.println(param.toString());

        QProductOption qProductOption = QProductOption.productOption;

        // 임시
        List<ProductOptionDto.ResponseSimple> optList = param.getPrdOptList();
        QProduct qProduct = QProduct.product;

        List<ProductDto.ResponseCustom> list = null;
        list = jpaQueryFactory.select(Projections.fields(ProductDto.ResponseCustom.class
                        , qProductOption.prdOptionSeq
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth
                        , qProductOption.intrRateType
                        , qProductOption.intrRateTypeNm
                        , qProductOption.saveTrm
                        , qProductOption.intrRate
                        , qProductOption.intrRate2
                        , qProductOption.rsrvType
                        , qProductOption.rsrvTypeNm
                        // custom
                        , ProductOptionExpression.retMaturityAmt(qProductOption).as("maturityAmt")

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
                        , Expressions.as(Expressions.constant(ProductExpression.retCnsmpInclnCdList(param.getCnsmpInclnCd())), "cnsmpInclnCdList")
                        , CodeExpression.retCodeNm(qProduct.actKindCd, "act_kind_cd").as("actKindNm")
                        , CodeExpression.retCodeNm(qProduct.joinWayCd, "join_way_cd").as("joinWayNm")
                ))
                .from(qProductOption)
                .innerJoin(qProduct)
                .on(qProduct.dclsMonth.eq(qProductOption.dclsMonth)
                        , qProduct.finCoNo.eq(qProductOption.finCoNo)
                        , qProduct.finPrdtCd.eq(qProductOption.finPrdtCd))
                .where(
                        ProductExpression.eqFinCoNo(qProduct, param.getFinCoNo())
                        , ProductExpression.eqFinPrdtCd(qProduct, param.getFinPrdtCd())
                        , ProductExpression.eqDclsMonth(qProduct, param.getDclsMonth())
                        , ProductExpression.eqJoinWayCd(qProduct, param.getJoinWayCd())
                        , ProductExpression.eqActKindCd(qProduct, param.getActKindCd())
                        , ProductExpression.inFinCoNoList(qProduct, param.getFinCoNoList())
                        , Expressions.list(qProductOption.prdOptionSeq
                                , qProductOption.finCoNo
                                , qProductOption.finPrdtCd
                                , qProductOption.dclsMonth).in(ProductOptionExpression.inPrdOptionList(optList))
                )
                .orderBy(ProductOptionExpression.orderSpecifiers(qProductOption, param.getCnsmpInclnCd(), qProduct.actKindCd))
                .limit(param.getLimit())
                .fetch();
        return list;
    }
}
