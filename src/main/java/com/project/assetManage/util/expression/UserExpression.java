package com.project.assetManage.util.expression;

import com.project.assetManage.entity.QUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class UserExpression {

    // 사용자seq Expression
    public static BooleanExpression eqUserSeq(QUser qUser, Long userSeq){
        if (ObjectUtils.isEmpty(userSeq)) return null;
        return qUser.userSeq.eq(userSeq);
    }

    // 직업군 Expression
    public static BooleanExpression eqOccupationCd(QUser qUser, String occupationCd){
        if (StringUtils.isEmpty(occupationCd)) return null;
        return qUser.occupationCd.eq(occupationCd);
    }

    // 연령대 Expression
    public static BooleanExpression eqAgeCd(QUser qUser, String ageCd){
        if (StringUtils.isEmpty(ageCd)) return null;
        return qUser.ageCd.eq(ageCd);
    }

    // 탈퇴여부 Expression
    public static BooleanExpression eqSecsnYn(QUser qUser, Character secsnYn){
        if (ObjectUtils.isEmpty(secsnYn)) return qUser.secsnYn.eq('N');
        return qUser.secsnYn.eq(secsnYn);
    }

}
