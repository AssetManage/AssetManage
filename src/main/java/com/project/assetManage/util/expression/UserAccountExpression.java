package com.project.assetManage.util.expression;

import com.project.assetManage.entity.QUserAccount;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class UserAccountExpression {

    // 사용자seq Expression
    public static BooleanExpression eqUserSeq(QUserAccount qUserAccount, Long userSeq){
        if (ObjectUtils.isEmpty(userSeq)) return null;
        return qUserAccount.userSeq.userSeq.eq(userSeq);
    }

    // 계좌번호 Expression
    public static BooleanExpression eqAccountNo(QUserAccount qUserAccount, String accountNo){
        if (StringUtils.isEmpty(accountNo)) return null;
        return qUserAccount.accountNo.eq(accountNo);
    }

    // 계좌순번 Expression
    public static BooleanExpression eqAccountSeq(QUserAccount qUserAccount, Integer accountSeq){
        if (ObjectUtils.isEmpty(accountSeq)) return null;
        return qUserAccount.accountSeq.eq(accountSeq);
    }

    // 사용여부 Expression
    public static BooleanExpression eqUseYn(QUserAccount qUserAccount, Character useYn){
        if (ObjectUtils.isEmpty(useYn)) return qUserAccount.useYn.eq('Y');
        return qUserAccount.useYn.eq(useYn);
    }

}
