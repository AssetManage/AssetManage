package com.project.assetManage.util.expression;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;

public class CodeExpression {
    // 공통코드명 Expression
    public static StringExpression retCodeNm(StringExpression column, String grpCodeId){
        return Expressions.stringTemplate("fn_get_code_nm({0}, {1})", grpCodeId, column);
    }
}
