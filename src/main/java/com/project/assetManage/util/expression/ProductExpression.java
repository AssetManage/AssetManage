package com.project.assetManage.util.expression;

import com.project.assetManage.entity.QProduct;
import com.project.assetManage.entity.QProductOption;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.*;
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
        if (StringUtils.isEmpty(joinWayCd)) return qProduct.joinWayCd.eq("A");
        return qProduct.joinWayCd.in(joinWayCd, "A");
    }
    // 금융회사코드 파라미터 목록 조건절 세팅
    public static BooleanExpression inFinCoNoList(QProduct qProduct, List<String> finCoNoList) {
        if(null != finCoNoList && !finCoNoList.isEmpty()){
            List<Expression> tuples = new ArrayList<>();
            for(String finCoNo : finCoNoList) {
                tuples.add(Expressions.template(Object.class, "{0}", finCoNo));
            }
            return qProduct.finCoNo.in(tuples.toArray(new Expression[0]));
        }else{
            return null;
        }
    }
    // 소비유형코드목록 Expression
    public static String retCnsmpInclnCdList(String cnsmpInclnCd){
        return switch (cnsmpInclnCd) {
            case "GH" -> "GH|YL";
            case "YL" -> "GH|YL";
            case "BP" -> "BP";
            case "AT" -> "SR|AT";
            case "SR" -> "SR|AT";
            default -> "AT";
        };
        // TO-DO :: 상품이 소속되는 소비유형코드 세분화
        // case1 :: 상품 옵션 목록이 하나인 경우,

        // case2 :: BP의 상품과 타 소비유형 상품이 중복되는 경우,
    }
}
