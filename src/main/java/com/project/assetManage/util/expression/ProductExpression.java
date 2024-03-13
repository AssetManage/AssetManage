package com.project.assetManage.util.expression;

import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.*;
import com.project.assetManage.util.exception.CustomException;
import com.project.assetManage.util.exception.ErrorCode;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductExpression {
    // 금융회사코드 Expression
    public static BooleanExpression eqFinCoNo(QProduct qProduct, String finCoNo) {
        if (StringUtils.isEmpty(finCoNo)) return null;
        return qProduct.finCoNo.eq(finCoNo);
    }
    // 금융상품코드 Expression
    public static BooleanExpression eqFinPrdtCd(QProduct qProduct, String finPrdtCd) {
        if (StringUtils.isEmpty(finPrdtCd)) return null;
        return qProduct.finPrdtCd.eq(finPrdtCd);
    }
    // 공시제출월[YYYYMM] Expression
    public static BooleanExpression eqDclsMonth(QProduct qProduct, String dclsMonth) {
        if (StringUtils.isEmpty(dclsMonth)) return null;
        return qProduct.dclsMonth.eq(dclsMonth);
    }
    // 상품분류코드 Expression
    public static BooleanExpression eqActKindCd(QProduct qProduct, String actKindCd) {
        if (StringUtils.isEmpty(actKindCd)) return null;
        return qProduct.actKindCd.eq(actKindCd);
    }
    // 가입방법코드 Expression
    public static BooleanExpression eqJoinWayCd(QProduct qProduct, String joinWayCd) {
        if (StringUtils.isEmpty(joinWayCd)) return null;
        return qProduct.joinWayCd.in(joinWayCd, "A");
    }
    // 금융회사코드 파라미터 목록 조건절 세팅
    public static BooleanExpression inFinCoNoList(QProduct qProduct, List<String> finCoNoList) {
        if(ObjectUtils.isEmpty(finCoNoList)){
            return null;
        }else{
            List<Expression> tuples = new ArrayList<>();
            for(String finCoNo : finCoNoList) {
                tuples.add(Expressions.template(Object.class, "{0}", finCoNo));
            }
            return qProduct.finCoNo.in(tuples.toArray(new Expression[0]));
        }
    }

    // 내부 회원 중 특정 연령대의 해당 상품 보유 갯수 subquery
    public static Expression retProductCntAgeCd(QProduct qProduct, String ageCd, String cnsmpInclnCd){
        QUserAccount qUserAccount = QUserAccount.userAccount;
        QUser qUser = QUser.user;
        QUserComuteIncome qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        return JPAExpressions.select(
                    Wildcard.count
                )
                .from(qUserAccount)
                .innerJoin(qUser)
                .on(
                        qUser.userSeq.eq(qUserAccount.userSeq.userSeq)
                        , UserExpression.eqAgeCd(qUser, ageCd)
                )
                .innerJoin(qUserComuteIncome)
                .on(
                        qUserComuteIncome.userSeq.eq(qUser.userSeq)
                )
                .where( qUserAccount.finPrdtCd.eq(qProduct.finPrdtCd)
                        , qUserAccount.finCoNo.eq(qProduct.finCoNo)
                        , qUserAccount.dclsMonth.eq(qProduct.dclsMonth)
                        , UserComputeIncomeExpression.eqCnsmpInclnCd(qUserComuteIncome, cnsmpInclnCd)
                )
                // .groupBy(qUserAccount.finCoNo, qUserAccount.finPrdtCd, qUserAccount.dclsMonth)
        ;
    }
    // 내부 회원 중 특정 소득범위의 해당 상품 보유 갯수 subquery
    public static Expression retProductCntIncomeScopeCd(QProduct qProduct, String incomeScopeCd, String cnsmpInclnCd){
        QUserAccount qUserAccount = QUserAccount.userAccount;
        QUserComuteIncome qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        return JPAExpressions.select(
                        Wildcard.count
                )
                .from(qUserAccount)
                .innerJoin(qUserComuteIncome)
                .on(
                        qUserComuteIncome.userSeq.eq(qUserAccount.userSeq.userSeq)
                        , UserComputeIncomeExpression.eqIncomeScopeCd(qUserComuteIncome, incomeScopeCd)
                        , UserComputeIncomeExpression.eqCnsmpInclnCd(qUserComuteIncome, cnsmpInclnCd)
                )
                .where( qUserAccount.finPrdtCd.eq(qProduct.finPrdtCd)
                        , qUserAccount.finCoNo.eq(qProduct.finCoNo)
                        , qUserAccount.dclsMonth.eq(qProduct.dclsMonth)
                )
                // .groupBy(qUserAccount.finCoNo, qUserAccount.finPrdtCd, qUserAccount.dclsMonth)
                ;
    }

    // 추천항목코드에 따른 상품 목록 갯수 subquery 분기
    // 5. 연령별(AG) (로그인한 회원과 동일한 연령대의 내부 회원이 많이 보유한 상품을 우선 정렬한다)
    // 5. 소득별(IC) (로그인한 회원과 동일한 소득범위의 내부 회원이 많이 보유한 상품을 우선 정렬한다)
    public static Expression retProductCnt(QProduct qProduct, ProductOptionDto.Request param) {
        if(StringUtils.isEmpty(param.getPrdtRcmdItemCd())) param.setPrdtRcmdItemCd("");

        if (param.getPrdtRcmdItemCd().equals("AG")) {
            if(StringUtils.isEmpty(param.getAgeCd())) throw new CustomException(ErrorCode.AGE_CD_IS_NULL);
            return retProductCntAgeCd(qProduct, param.getAgeCd(), param.getCnsmpInclnCd());
        }else if(param.getPrdtRcmdItemCd().equals("IC")){
            if(StringUtils.isEmpty(param.getIncomeScopeCd())) throw new CustomException(ErrorCode.INCOME_SCOPE_CD_IS_NULL);
            return retProductCntIncomeScopeCd(qProduct, param.getIncomeScopeCd(), param.getCnsmpInclnCd());
        }else return Expressions.nullExpression();
    }
}
