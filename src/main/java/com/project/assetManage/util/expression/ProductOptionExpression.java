package com.project.assetManage.util.expression;

import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.QProductOption;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductOptionExpression {
    // 금융회사코드 Expression
    public static BooleanExpression eqFinCoNo(QProductOption qProductOption, String finCoNo) {
        if (StringUtils.isEmpty(finCoNo)) return null;
        return qProductOption.finCoNo.eq(finCoNo);
    }
    // 금융상품코드 Expression
    public static BooleanExpression eqFinPrdtCd(QProductOption qProductOption, String finPrdtCd) {
        if (StringUtils.isEmpty(finPrdtCd)) return null;
        return qProductOption.finPrdtCd.eq(finPrdtCd);
    }
    // 공시제출월[YYYYMM] Expression
    public static BooleanExpression eqDclsMonth(QProductOption qProductOption, String dclsMonth) {
        if (StringUtils.isEmpty(dclsMonth)) return null;
        return qProductOption.dclsMonth.eq(dclsMonth);
    }
    // 상품옵션순번 Expression
    public static BooleanExpression eqPrdOptionSeq(QProductOption qProductOption, Long prdOptionSeq) {
        if (null == prdOptionSeq) return null;
        return qProductOption.prdOptionSeq.eq(prdOptionSeq);
    }
    // 소비유형코드에 따른 상품옵션시퀀스 Expression
    public static NumberExpression<Long> retPrdOptionSeq(QProductOption qProductOption, String cnsmpInclnCd) {
        return switch (cnsmpInclnCd) {
            case "GH" -> qProductOption.prdOptionSeq.min();
            case "YL" -> qProductOption.prdOptionSeq.min();
            case "BP" -> qProductOption.prdOptionSeq.avg().floor().castToNum(Long.class);
            case "AT" -> qProductOption.prdOptionSeq.max();
            case "SR" -> qProductOption.prdOptionSeq.max();
            default -> qProductOption.prdOptionSeq.max();
        };
    }
    // 적립유형코드 Expression
    public static BooleanExpression eqRsrvType(QProductOption qProductOption, String rsrvType) {
        if (StringUtils.isEmpty(rsrvType)) return null;
        return qProductOption.rsrvType.eq(rsrvType);
    }

    // 저축금리유형에 따른 예상만기금액 Expression
    public static NumberExpression<Integer> retMaturityAmt(QProductOption qProductOption){
        NumberExpression<Integer> intrRate2 = qProductOption.intrRate2.castToNum(Integer.class);
        NumberExpression<Integer> saveTrm = qProductOption.saveTrm;

        // 단리(S)
        // FLOOR(100 + 100 * a.intr_rate2 * (a.save_trm/12))
        if(qProductOption.intrRateType.equals("S")){
            return intrRate2.multiply(saveTrm.divide(12)).multiply(100).add(100).floor();
        }else{
            // 복리(M)
            // FLOOR(100 * POW((1 + a.intr_rate2), (a.save_trm/12)))
            NumberTemplate pow = Expressions.numberTemplate(Integer.class,"POW({0}, {1})", qProductOption.intrRate2.add(1), saveTrm.divide(12));
            return pow.multiply(100);
        }
    }

    // 추출된 상품옵션순번 목록 서브 쿼리 조건절 세팅
    public static Expression[] inPrdOptionList(List<ProductOptionDto.ResponseSimple> productOptionList) {

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
    // 소비유형코드목록 Expression
    // TO-DO :: 단순히 순번 비교를 통한 추가로 필터링 필요
    public static Expression retCnsmpInclnCdList(QProductOption qProductOption, QProductOption qProductOptionSub, String cnsmpInclnCd){
        // TO-DO :: 상품이 소속되는 소비유형코드 세분화
        // case1 :: 상품 옵션 목록이 하나인 경우,
        // case2 :: BP의 상품과 타 소비유형 상품이 중복되는 경우,
        /*
        * select case when t.max_prd_option_seq = min_prd_option_seq then 'GH|YL|BP|AT|SR'
                        when t.max_prd_option_seq = avg_prd_option_seq then 'AT|SR|BP'
                        when t.min_prd_option_seq = avg_prd_option_seq then 'GH|YL|BP'
                        else '기존 세팅된 코드'
                         end cnsmp_incln_cd_list
                 , t.fin_co_no
                 , t.fin_prdt_cd
                 , t.dcls_month
                 , p.fin_prdt_nm
              from ( select MAX(sub.prd_option_seq) as max_prd_option_seq
                         , MIN(sub.prd_option_seq) as min_prd_option_seq
                         , FLOOR(AVG(sub.prd_option_seq)) as avg_prd_option_seq
                         , sub.fin_co_no
                         , sub.fin_prdt_cd
                         , sub.dcls_month
                      from api_products_option sub
                     group by sub.fin_co_no, sub.fin_prdt_cd, sub.dcls_month
                    ) t
                    inner join api_products p
                            on p.fin_co_no = t.fin_co_no
                           and p.fin_prdt_cd = t.fin_prdt_cd
                           and p.dcls_month = t.dcls_month
             order by p.fin_prdt_nm
        * */

        cnsmpInclnCd = switch (cnsmpInclnCd) {
            case "GH" -> "GH|YL";
            case "YL" -> "GH|YL";
            case "BP" -> "BP";
            case "AT" -> "SR|AT";
            case "SR" -> "SR|AT";
            default -> "AT";
        };

        return JPAExpressions.select( new CaseBuilder()
                        .when(qProductOptionSub.prdOptionSeq.max().eq(qProductOptionSub.prdOptionSeq.min()))
                        .then(Expressions.constant("GH|YL|BP|AT|SR"))
                        .when(qProductOptionSub.prdOptionSeq.max().eq(retPrdOptionSeq(qProductOptionSub, "BP")))
                        .then(Expressions.constant("AT|SR|BP"))
                        .when(qProductOptionSub.prdOptionSeq.min().eq(retPrdOptionSeq(qProductOptionSub, "BP")))
                        .then(Expressions.constant("GH|YL|BP"))
                        .otherwise(Expressions.constant(cnsmpInclnCd)).as("cnsmpInclnCdList")
                )
                .from(qProductOptionSub)
                .where(qProductOption.dclsMonth.eq(qProductOptionSub.dclsMonth)
                        , qProductOption.finCoNo.eq(qProductOptionSub.finCoNo)
                        , qProductOption.finPrdtCd.eq(qProductOptionSub.finPrdtCd))
        // .groupBy(qProductOptionSub.dclsMonth, qProductOptionSub.finCoNo, qProductOptionSub.finPrdtCd)
        ;
    }

    // 소비유형과 부가 항목에 따른 상품 목록 정렬
    // 선순위 :: 소비유형 , 후순위 :: 연령별, 소득별
    public static OrderSpecifier[] orderSpecifiers(QProductOption qProductOption, String cnsmpInclnCd, StringPath actKindCd) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        // 정렬 선순위조건 :: 소비유형
        // 1. save_trm
        // 배짱이형, 욜로형 :: save_trm 낮은 순, 그 외 :: save_trm 높은 순
        switch (cnsmpInclnCd) {
            case "GH" -> orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, qProductOption.saveTrm)); // order by apo.save_trm asc, apo.maturity_amt desc, field(apo.intr_rate_type, 'M')
            case "YL" -> orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, qProductOption.saveTrm)); // order by apo.save_trm asc, apo.maturity_amt desc, field(apo.intr_rate_type, 'M')
            case "BP" -> orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, qProductOption.saveTrm)); // order by apo.save_trm desc, apo.maturity_amt desc, field(apo.intr_rate_type, 'M')
            case "AT" -> orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, qProductOption.saveTrm)); // order by apo.save_trm desc, apo.maturity_amt desc, field(apo.intr_rate_type, 'M')
            case "SR" -> orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, qProductOption.saveTrm)); // order by apo.save_trm desc, apo.maturity_amt desc, field(apo.intr_rate_type, 'M')
            // default -> new OrderSpecifier<>(Order.ASC, qProductOption.saveTrm);
        }
        // 2. 예상 만기금액이 높은 순
        orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, retMaturityAmt(qProductOption)));
        // 3. intr_rate_type 복리(M) 우선
        orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, qProductOption.intrRateType));
        // 4. rsrv_type
        // 적금인 경우, 적립유형코드 추가
        // 배짱이형, 욜로형 :: rsrv_type 자유적립식(F), 그 외 :: rsrv_type 정액적립식(S)
        if(actKindCd.equals("SV")){
            switch (cnsmpInclnCd) {
                case "GH" -> orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, qProductOption.rsrvType));
                case "YL" -> orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, qProductOption.rsrvType));
                case "BP" -> orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, qProductOption.rsrvType));
                case "AT" -> orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, qProductOption.rsrvType));
                case "SR" -> orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, qProductOption.rsrvType));
                // default -> new OrderSpecifier<>(Order.ASC, qProductOption.saveTrm);
            }
        }
        // TO-DO :: 정렬 후순위조건
        // 5. 연령별 (로그인한 회원과 동일한 연령대의 내부 회원이 많이 보유한 상품을 우선 정렬한다)

        // 5. 소득별 (로그인한 회원과 동일한 소득범위의 내부 회원이 많이 보유한 상품을 우선 정렬한다)

        // TO-DO :: 정렬 조건의 값이 동일한 상품인 경우, 은행이름순이나, 추후 추가될 광고 상품 우선 정렬 로직 추가
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }
}
