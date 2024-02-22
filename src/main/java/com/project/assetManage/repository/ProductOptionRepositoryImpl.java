package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.QProduct;
import com.project.assetManage.entity.QProductOption;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductOptionRepositoryImpl implements ProductOptionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductOptionDto.ResponseAll> selectProductOptionList(Map<String, Object> param) {
        System.out.println("selectProductOptionList ====================================");
        System.out.println(param.toString());

        QProductOption qProductOption = QProductOption.productOption;
        // QProduct qProduct = new QProduct("product");

        // QProductOption qProductOptionSub = new QProductOption("sub");
        List<ProductOptionDto.ResponseAll> list = null;
        List<ProductOptionDto.ResponseSimple> prdOptionList = this.selectProductOptionListSub(param);
        // case1 :: where
        list = jpaQueryFactory.select(Projections.fields(ProductOptionDto.ResponseAll.class
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
                        , retMaturityAmt(qProductOption).as("maturityAmt")
                ))
                .from(qProductOption)
                .where(
                        Expressions.list(qProductOption.prdOptionSeq
                                , qProductOption.finCoNo
                                , qProductOption.finPrdtCd
                                , qProductOption.dclsMonth).in(inPrdOptionList(prdOptionList))
                )
                .fetch();
        // case2 :: inner join

        return list;
    }

    @Override
    public List<ProductOptionDto.ResponseSimple> selectProductOptionListSub(Map<String, Object> param) {
        System.out.println("selectProductOptionListSub ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        // 상품별 옵션 조회시 save_trm이 가장 max인 것을 기본값으로 조회
        // 소비유형코드로 분기
        String cnsmpInclnCd = "AT";
        if(null != param.get("cnsmpInclnCd")) cnsmpInclnCd = param.get("cnsmpInclnCd").toString();
        System.out.println("cnsmpInclnCd ====================================");
        System.out.println(cnsmpInclnCd);

        // 적금인 경우, 적립유형코드 추가
        String rsrvType = null;
        if(null != param.get("rsrvType")) rsrvType = param.get("rsrvType").toString();
        System.out.println("rsrvType ====================================");
        System.out.println(rsrvType);

        // 소비유형코드에 따라 save_trm max, min, avg 조회
        QProductOption qProductOption = QProductOption.productOption;
        List<ProductOptionDto.ResponseSimple> list = null;
        list = jpaQueryFactory.select(Projections.fields(ProductOptionDto.ResponseSimple.class,
                        retPrdOptionSeq(qProductOption, cnsmpInclnCd).as("prdOptionSeq")
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth))
                .from(qProductOption)
                .where(eqRsrvType(qProductOption, rsrvType))
                .groupBy(qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth)
                .fetch();

        System.out.println("list ====================================");
        System.out.println(list.toString());
        return list;
    }

    // CUSTOM
    // 소비유형코드에 따른 상품옵션시퀀스 Expression
    private NumberExpression<Long> retPrdOptionSeq(QProductOption qProductOption, String cnsmpInclnCd) {
        if(cnsmpInclnCd.equals("GH") || cnsmpInclnCd.equals("YL")){
            return qProductOption.prdOptionSeq.min();
        }else if(cnsmpInclnCd.equals("BP")){
            return qProductOption.prdOptionSeq.avg().floor().castToNum(Long.class);
        }
        return qProductOption.prdOptionSeq.max();
    }
    // 저축금리유형에 따른 예상만기금액 Expression
    private NumberExpression<Integer> retMaturityAmt(QProductOption qProductOption){
        NumberExpression<Integer> intrRate2 = qProductOption.intrRate2.castToNum(Integer.class);
        NumberExpression<Integer> saveTrm = qProductOption.saveTrm;
        // 단리
        // FLOOR(100 + 100 * a.intr_rate2 * (a.save_trm/12))
        if(qProductOption.intrRateType.equals("S")){
            return intrRate2.multiply(saveTrm.divide(12)).multiply(100).add(100).floor();
        }else{
            // 복리
            // FLOOR(100 * POW((1 + a.intr_rate2), (a.save_trm/12)))
            NumberTemplate pow = Expressions.numberTemplate(Integer.class,"POW({0}, {1})", qProductOption.intrRate2, saveTrm.divide(12));
            return pow.multiply(100);
        }
    }
    // 적립유형코드 Expression
    private BooleanExpression eqRsrvType(QProductOption qProductOption, String rsrvType) {
        if (StringUtils.isEmpty(rsrvType)) {
            return null;
        }
        return qProductOption.rsrvType.eq(rsrvType);
    }
    
    // case1 :: 추출된 서브 쿼리 조건절
    private Expression[] inPrdOptionList(List<ProductOptionDto.ResponseSimple> productOptionList) {

        List<Expression> tuples = new ArrayList<>();

        for(ProductOptionDto.ResponseSimple productOption : productOptionList) {
            tuples.add(Expressions.template(Object.class, "(({0}, {1}, {2}, {3}))"
                    , productOption.getPrdOptionSeq()
                    , productOption.getFinCoNo()
                    , productOption.getFinPrdtCd()
                    , productOption.getDclsMonth()));
        }

        return tuples.toArray(new Expression[0]);
    }

    // TO-DO :: case2 :: 추출된 서브 쿼리 join절
    private JPQLQuery<ProductOptionDto.ResponseSimple> selectPrdOptionListForCnsmpInclnCd(QProductOption qProductOption){
        return JPAExpressions
                .select(Projections.fields(ProductOptionDto.ResponseSimple.class, qProductOption.dclsMonth))
                .from(qProductOption);
    }
}
