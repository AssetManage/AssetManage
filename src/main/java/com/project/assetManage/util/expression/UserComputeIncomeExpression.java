package com.project.assetManage.util.expression;

import com.project.assetManage.entity.QUserComuteIncome;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;

public class UserComputeIncomeExpression {

    // 소득범위코드 Expression
    public static BooleanExpression eqIncomeScopeCd(QUserComuteIncome qUserComuteIncome, String incomeScopeCd){
        if (null == incomeScopeCd) return null;
        return qUserComuteIncome.incomeScopeCd.eq(incomeScopeCd);
    }

    // 소비성향코드 Expression
    public static BooleanExpression eqCnsmpInclnCd(QUserComuteIncome qUserComuteIncome, String cnsmpInclnCd){
        if (StringUtils.isEmpty(cnsmpInclnCd)) return null;
        return qUserComuteIncome.cnsmpInclnCd.eq(cnsmpInclnCd);
    }

}
