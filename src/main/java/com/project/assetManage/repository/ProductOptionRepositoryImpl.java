package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.QProductOption;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
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
    public List<ProductOptionDto> selectProductOptionList(Map<String, Object> param) {
        System.out.println("selectProductOptionList ====================================");
        System.out.println(param.toString());

        QProductOption qProductOption = QProductOption.productOption;
        // QProductOption qProductOptionSub = new QProductOption("sub");
        List<ProductOptionDto> list = null;
        List<ProductOptionDto> prdOptionList = this.selectProductOptionListSub(param);
        // case1 :: where
        list = jpaQueryFactory.select(Projections.fields(ProductOptionDto.class
                        , qProductOption.prdOptionSeq
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.intrRateType
                        , qProductOption.intrRateTypeNm
                        , qProductOption.saveTrm
                        , qProductOption.intrRate
                        , qProductOption.intrRate2
                        , qProductOption.rsrvType
                        , qProductOption.rsrvTypeNm
                        // custom
                /*
                        , new CaseBuilder().when(qProductOption.intrRateType.eq("S"))
                                           .then(100 + 100 * qProductOption.intrRate2.multiply(qProductOption.saveTrm.castToNum(Long.class).divide(12))) // 단리
                                           .otherwise(100 * Math.pow((1 + qProductOption.intrRate2), (qProductOption.saveTrm.divide(12)))  ) // 복리
                                .as("maturityAmt")

                 */
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
    public List<ProductOptionDto> selectProductOptionListSub(Map<String, Object> param) {
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
        List<ProductOptionDto> list = null;
        list = jpaQueryFactory.select(Projections.fields(ProductOptionDto.class,
                        retPrdOptionSeq(qProductOption, cnsmpInclnCd)
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
            return qProductOption.prdOptionSeq.min().as("prdOptionSeq");
        }else if(cnsmpInclnCd.equals("BP")){
            return qProductOption.prdOptionSeq.avg().floor().castToNum(Long.class).as("prdOptionSeq");
        }
        return qProductOption.prdOptionSeq.max().as("prdOptionSeq");
    }
    // 적립유형코드 Expression
    private BooleanExpression eqRsrvType(QProductOption qProductOption, String rsrvType) {
        if(StringUtils.isEmpty(rsrvType)) {
            return null;
        }
        return qProductOption.rsrvType.eq(rsrvType);
    }
    
    // case1 :: 추출된 서브 쿼리 조건절
    private Expression[] inPrdOptionList(List<ProductOptionDto> productOptionList) {

        List<Expression> tuples = new ArrayList<>();

        for(ProductOptionDto productOption : productOptionList) {
            tuples.add(Expressions.template(Object.class, "(({0}, {1}, {2}, {3}))"
                    , productOption.getPrdOptionSeq()
                    , productOption.getFinCoNo()
                    , productOption.getFinPrdtCd()
                    , productOption.getDclsMonth()));
        }

        return tuples.toArray(new Expression[0]);
    }
}
