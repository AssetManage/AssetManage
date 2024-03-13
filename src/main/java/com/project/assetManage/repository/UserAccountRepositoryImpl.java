package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.UserAccountDto;
import com.project.assetManage.entity.QProduct;
import com.project.assetManage.entity.QUser;
import com.project.assetManage.entity.QUserAccount;
import com.project.assetManage.entity.QUserComuteIncome;
import com.project.assetManage.util.expression.CodeExpression;
import com.project.assetManage.util.expression.UserAccountExpression;
import com.project.assetManage.util.expression.UserComputeIncomeExpression;
import com.project.assetManage.util.expression.UserExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserAccountRepositoryImpl implements UserAccountRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // 회원이 보유한 예적금 상품 목록
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

    // 회원이 보유한 예적금 상품과 상품 정보 통합 목록
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

    // 내부 회원 중 특정 연령대가 보유한 예적금 상품 목록 :: 보유한 상품이 많은 순 우선 정렬
    // TO-DO :: 갯수나 백분율 사용할 것인지 추후 협의
    @Override
    public List<ProductDto.ResponseSimple> selectProductListRcmdAgeCd(UserAccountDto.Request param) {
        QUserAccount qUserAccount = QUserAccount.userAccount;
        QUser qUser = QUser.user;
        QUserComuteIncome  qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        List<ProductDto.ResponseSimple> list = jpaQueryFactory
                .select(Projections.fields(ProductDto.ResponseSimple.class
                                , qUserAccount.finPrdtCd
                                , qUserAccount.finCoNo
                                , qUserAccount.dclsMonth
                        )
                )
                .from(qUserAccount)
                .innerJoin(qUser)
                .on(
                        qUser.userSeq.eq(qUserAccount.userSeq.userSeq)
                        , UserExpression.eqAgeCd(qUser, param.getAgeCd())
                )
                .innerJoin(qUserComuteIncome)
                .on(
                        qUserComuteIncome.userSeq.eq(qUser.userSeq)
                        , UserComputeIncomeExpression.eqCnsmpInclnCd(qUserComuteIncome, param.getCnsmpInclnCd())
                )
                .groupBy(qUserAccount.finCoNo, qUserAccount.finPrdtCd, qUserAccount.dclsMonth)
                .orderBy(qUserAccount.count().desc())
                .fetch();

        return list;
    }

    // 내부 회원 중 특정 소득범위가 보유한 예적금 상품 목록 :: 보유한 상품이 많은 순 우선 정렬
    // TO-DO :: 갯수나 백분율 사용할 것인지 추후 협의
    @Override
    public List<ProductDto.ResponseSimple> selectProductListRcmdIncomeScopeCd(UserAccountDto.Request param) {
        QUserAccount qUserAccount = QUserAccount.userAccount;
        QUserComuteIncome  qUserComuteIncome = QUserComuteIncome.userComuteIncome;

        List<ProductDto.ResponseSimple> list = jpaQueryFactory
                .select(Projections.fields(ProductDto.ResponseSimple.class
                                , qUserAccount.finPrdtCd
                                , qUserAccount.finCoNo
                                , qUserAccount.dclsMonth
                        )
                )
                .from(qUserAccount)
                .innerJoin(qUserComuteIncome)
                .on(
                        qUserComuteIncome.userSeq.eq(qUserAccount.userSeq.userSeq)
                        , UserComputeIncomeExpression.eqIncomeScopeCd(qUserComuteIncome, param.getIncomeScopeCd())
                        , UserComputeIncomeExpression.eqCnsmpInclnCd(qUserComuteIncome, param.getCnsmpInclnCd())
                )
                .groupBy(qUserAccount.finCoNo, qUserAccount.finPrdtCd, qUserAccount.dclsMonth)
                .orderBy(qUserAccount.count().desc())
                .fetch();

        return list;
    }
}