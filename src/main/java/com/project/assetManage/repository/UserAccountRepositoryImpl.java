package com.project.assetManage.repository;

import com.project.assetManage.dto.UserAccountDto;
import com.project.assetManage.entity.QProduct;
import com.project.assetManage.entity.QUserAccount;
import com.project.assetManage.util.expression.CodeExpression;
import com.project.assetManage.util.expression.UserAccountExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserAccountRepositoryImpl implements UserAccountRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserAccountDto.ResponseAll> selectUserAccountList(UserAccountDto.Request param) {
        QUserAccount qUserAccount = QUserAccount.userAccount;

        List<UserAccountDto.ResponseAll> list = jpaQueryFactory
                .select(Projections.fields(UserAccountDto.ResponseAll.class
                        , qUserAccount.accountNo
                        , qUserAccount.userSeq.userSeq
                        , qUserAccount.finPrdtCd
                        , qUserAccount.finCoNo
                        , qUserAccount.dclsMonth
                        , qUserAccount.accountSeq
                        , qUserAccount.accountIssueDate
                        , qUserAccount.maturityDate
                        , qUserAccount.lastTranDate
                        , qUserAccount.productNm
                        , qUserAccount.productSubName
                        , qUserAccount.balanceAmt
                        , qUserAccount.useYn)
                )
                .from(qUserAccount)
                .where(
                        UserAccountExpression.eqAccountSeq(qUserAccount, param.getAccountSeq())
                        , UserAccountExpression.eqAccountNo(qUserAccount, param.getAccountNo())
                        , UserAccountExpression.eqUserSeq(qUserAccount, param.getUserSeq())
                        , UserAccountExpression.eqUseYn(qUserAccount, param.getUseYn())
                )
                .fetch();

        return list;
    }

    @Override
    public List<UserAccountDto.ResponseCustom> selectUserAccountListWithProduct(UserAccountDto.Request param) {
        QUserAccount qUserAccount = QUserAccount.userAccount;
        QProduct qProduct = QProduct.product;

        List<UserAccountDto.ResponseCustom> list = jpaQueryFactory
                .select(Projections.fields(UserAccountDto.ResponseCustom.class
                        , qUserAccount.accountNo
                        , qUserAccount.userSeq.userSeq
                        , qUserAccount.finPrdtCd
                        , qUserAccount.finCoNo
                        , qUserAccount.dclsMonth
                        , qUserAccount.accountSeq
                        , qUserAccount.accountIssueDate
                        , qUserAccount.maturityDate
                        , qUserAccount.lastTranDate
                        , qUserAccount.productNm
                        , qUserAccount.productSubName
                        , qUserAccount.balanceAmt
                        , qUserAccount.useYn

                        // product
                        , qProduct.actKindCd
                        , qProduct.korCoNm
                        , qProduct.finPrdtNm
                        , qProduct.joinWay
                        , qProduct.mtrtInt
                        , qProduct.spclCnd
                        , qProduct.joinDeny
                        , qProduct.joinMember
                        , qProduct.etcNote
                        , qProduct.maxLmit
                        , qProduct.dclsStrtDay
                        , qProduct.dclsEndDay
                        , qProduct.finCoSubmDay
                        , qProduct.joinWayCd
                        , CodeExpression.retCodeNm(qProduct.actKindCd, "act_kind_cd").as("actKindNm")
                        , CodeExpression.retCodeNm(qProduct.joinWayCd, "join_way_cd").as("joinWayNm")
                    )
                )
                .from(qUserAccount)
                .leftJoin(qProduct)
                .on(qProduct.finCoNo.eq(qUserAccount.finCoNo)
                        , qProduct.finPrdtCd.eq(qUserAccount.finPrdtCd)
                        , qProduct.dclsMonth.eq(qUserAccount.dclsMonth)
                )
                .where(
                        UserAccountExpression.eqAccountSeq(qUserAccount, param.getAccountSeq())
                        , UserAccountExpression.eqAccountNo(qUserAccount, param.getAccountNo())
                        , UserAccountExpression.eqUserSeq(qUserAccount, param.getUserSeq())
                        , UserAccountExpression.eqUseYn(qUserAccount, param.getUseYn())
                )
                .fetch();

        return list;
    }
}
