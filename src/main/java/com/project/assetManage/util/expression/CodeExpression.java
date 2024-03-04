package com.project.assetManage.util.expression;

import com.project.assetManage.entity.QProduct;
import com.project.assetManage.entity.common.QCode;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CodeExpression {

    // 공통코드명 Expression
    public static StringExpression retCodeNm(StringExpression column, String grpCodeId){
        return Expressions.stringTemplate("fn_get_code_nm({0}, {1})", grpCodeId, column);
    }

    // 그룹코드id Expression
    public static BooleanExpression eqGroupCode(QCode qCode, String groupCode){
        if (StringUtils.isEmpty(groupCode)) return null;
        return qCode.groupCode.eq(groupCode);
    }

    // 코드id Expression
    public static BooleanExpression eqCodeId(QCode qCode, String codeId){
        if (StringUtils.isEmpty(codeId)) return null;
        return qCode.codeId.eq(codeId);
    }

    // 사용여부 Expression
    public static BooleanExpression eqUseYn(QCode qCode, Character useYn){
        if (null == useYn) return null;
        return qCode.useYn.eq(useYn);
    }

    // 코드목록 string => list 조건절 세팅
    // codeIdList format :: "ex1|ex2|..."
    public static BooleanExpression inCnsmpInclnCdList(QCode qCode, String codeIdListStr) {
        List<String> codeIdList = List.of(codeIdListStr.split("\\|"));

        if(null != codeIdList && !codeIdList.isEmpty()){
            List<Expression> tuples = new ArrayList<>();
            for(String codeId : codeIdList) {
                tuples.add(Expressions.template(Object.class, "{0}", codeId));
            }
            return qCode.codeId.in(tuples.toArray(new Expression[0]));
        }else{
            return null;
        }
    }

}
