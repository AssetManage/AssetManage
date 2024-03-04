package com.project.assetManage.repository;

import com.project.assetManage.dto.CodeDto;
import com.project.assetManage.entity.common.QCode;
import com.project.assetManage.util.expression.CodeExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CodeRepositoryImpl implements CodeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CodeDto.ResponseAll> selectCodeList(CodeDto.Request param) {
        QCode qCode = QCode.code;
        List<CodeDto.ResponseAll> list =
                jpaQueryFactory.select(Projections.fields(CodeDto.ResponseAll.class
                                , qCode.groupCode
                                , qCode.codeId
                                , qCode.codeNm
                                , qCode.etc1
                                , qCode.etc2
                                , qCode.etc3
                                , qCode.rmk
                                , qCode.dispSeq
                                , qCode.useYn))
                        .from(qCode)
                        .where(
                                CodeExpression.eqGroupCode(qCode, param.getGroupCode())
                                , CodeExpression.eqCodeId(qCode, param.getCodeId())
                                , CodeExpression.eqUseYn(qCode, param.getUseYn())
                                , CodeExpression.inCnsmpInclnCdList(qCode, param.getCodeIdListStr())
                        )
                        .orderBy(qCode.dispSeq.asc())
                        .fetch();
        return list;
    }
}
