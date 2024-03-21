package com.project.assetManage.repository;

import com.project.assetManage.dto.UserDto;
import com.project.assetManage.entity.*;
import com.project.assetManage.util.expression.CodeExpression;
import com.project.assetManage.util.expression.UserExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UserDto.ResponseSimple selectUserSimple(UserDto.Request param) {
        QUser quser = QUser.user;
        QUserComuteIncome qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        UserDto.ResponseSimple ret =
                jpaQueryFactory.select(Projections.fields(UserDto.ResponseSimple.class
                                , quser.userSeq
                                , quser.userNm
                                , quser.prdtRcmdItemCd
                                , quser.ageCd
                                , qUserComuteIncome.cnsmpInclnCd
                                , qUserComuteIncome.incomeScopeCd

                                , CodeExpression.retCodeNm(quser.ageCd, "age_cd").as("ageNm")
                                , CodeExpression.retCodeNm(quser.prdtRcmdItemCd, "prdt_rcmd_item_cd").as("prdtRcmdItemNm")
                                , CodeExpression.retCodeNm(qUserComuteIncome.cnsmpInclnCd, "cnsmp_incln_cd").as("cnsmpInclnNm")
                                , CodeExpression.retCodeNm(qUserComuteIncome.incomeScopeCd, "income_scope_cd").as("incomeScopeNm")
                        ))
                        .from(quser)
                        .innerJoin(qUserComuteIncome)
                        .on(quser.userSeq.eq(qUserComuteIncome.userSeq))
                        .where(
                                UserExpression.eqUserSeq(quser, param.getUserSeq())
                                , UserExpression.eqAgeCd(quser, param.getAgeCd())
                                , UserExpression.eqSecsnYn(quser, 'N')
                        )
                        .fetchOne();
        return ret;
    }

    @Override
    public UserDto.ResponseAll selectUserAll(UserDto.Request param) {
        QUser quser = QUser.user;
        QUserComuteIncome qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        UserDto.ResponseAll ret = jpaQueryFactory.select(Projections.fields(UserDto.ResponseAll.class
                        , quser.userSeq
                        , quser.userNm
                        , quser.loginId
                        , quser.loginPw
                        , quser.email
                        , quser.prdtRcmdItemCd
                        , quser.lockYn
                        , quser.secsnYn
                        , quser.indvdlinfoAgreeYn
                        , quser.profileImgUrl
                        , quser.sexCd
                        , quser.ageCd
                        , quser.age
                        , quser.occupationCd
                        , quser.mobileTelNum
                        , quser.zipCd
                        , quser.zipDetailAddr1
                        , quser.zipDetailAddr2
                        , CodeExpression.retCodeNm(quser.prdtRcmdItemCd, "prdt_rcmd_item_cd").as("prdtRcmdItemNm")
                        , CodeExpression.retCodeNm(quser.occupationCd, "occupation_cd").as("occupationNm")

                        // UserComuteIncome
                        , qUserComuteIncome.cnsmpInclnCd
                        , qUserComuteIncome.incomeScopeCd
                        , CodeExpression.retCodeNm(qUserComuteIncome.incomeScopeCd, "income_scope_cd").as("incomeScopeNm")
                        , CodeExpression.retCodeNm(qUserComuteIncome.cnsmpInclnCd, "cnsmp_incln_cd").as("cnsmpInclnNm")
                ))
                .from(quser)
                .innerJoin(qUserComuteIncome)
                .on(quser.userSeq.eq(qUserComuteIncome.userSeq))
                .where(
                        UserExpression.eqUserSeq(quser, param.getUserSeq())
//                        , UserExpression.eqAgeCd(quser, param.getAgeCd())
                        , UserExpression.eqSecsnYn(quser, 'N')
                )
                .fetchOne();
        return ret;
    }

    @Override
    public List<UserDto.ResponseCustom> selectUserListWithComputeIncome(UserDto.Request param) {
        QUser quser = QUser.user;
        QUserComuteIncome qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        List<UserDto.ResponseCustom> list = jpaQueryFactory.select(Projections.fields(UserDto.ResponseCustom.class
                        , quser.userSeq
                        , quser.userNm
                        , quser.loginId
                        , quser.loginPw
                        , quser.email
                        , quser.prdtRcmdItemCd
                        , quser.lockYn
                        , quser.secsnYn
                        , quser.indvdlinfoAgreeYn
                        , quser.profileImgUrl
                        , quser.sexCd
                        , quser.ageCd
                        , quser.age
                        , quser.occupationCd
                        , quser.mobileTelNum
                        , quser.zipCd
                        , quser.zipDetailAddr1
                        , quser.zipDetailAddr2
                        , CodeExpression.retCodeNm(quser.prdtRcmdItemCd, "prdt_rcmd_item_cd").as("prdtRcmdItemNm")
                        , CodeExpression.retCodeNm(quser.occupationCd, "occupation_cd").as("occupationNm")

                        // UserComuteIncome
                        , qUserComuteIncome.cnsmpInclnCd
                        , qUserComuteIncome.incomeScopeCd
                        , qUserComuteIncome.yearIncome
                        , qUserComuteIncome.cardExpdtAmt
                        , qUserComuteIncome.cashExpdtAmt
                        , qUserComuteIncome.savingExpdtAmt
                        , CodeExpression.retCodeNm(qUserComuteIncome.incomeScopeCd, "income_scope_cd").as("incomeScopeNm")
                        , CodeExpression.retCodeNm(qUserComuteIncome.cnsmpInclnCd, "cnsmp_incln_cd").as("cnsmpInclnNm")
                ))
                .from(quser)
                .innerJoin(qUserComuteIncome)
                .on(quser.userSeq.eq(qUserComuteIncome.userSeq))
                .where(
                        UserExpression.eqUserSeq(quser, param.getUserSeq())
                        , UserExpression.eqAgeCd(quser, param.getAgeCd())
                        , UserExpression.eqSecsnYn(quser, 'N')
                )
                .fetch();

        return list;
    }


}
