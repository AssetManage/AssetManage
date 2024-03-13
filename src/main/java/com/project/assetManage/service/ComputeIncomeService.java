package com.project.assetManage.service;

import com.project.assetManage.entity.*;
import com.project.assetManage.entity.common.ConsumptionInclination;
import com.project.assetManage.entity.common.QConsumptionInclination;
import com.project.assetManage.repository.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComputeIncomeService{

    private ComputeIncomeRepository computeIncomeRepository;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ComputeIncomeService(ComputeIncomeRepository computeIncomeRepository, EntityManager entityManager) {
        this.computeIncomeRepository = computeIncomeRepository;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Transactional
    public void insertComputeIncome(String userSeq) {
        // 계좌정보 조회
        QUser User = QUser.user;
        QUserAccount userAccount = QUserAccount.userAccount;
        QAccountDetail apiAccountDetail = QAccountDetail.accountDetail;

        List<UserAccount> userAccounts = queryFactory
                .selectFrom(userAccount)
                .where(userAccount.userSeq.userSeq.eq(Long.valueOf(userSeq)))
                .fetch();

        // 산출소득 초기화
        UserComuteIncome computeIncome = new UserComuteIncome();
        computeIncome.setUserSeq(Long.valueOf(userSeq));
        computeIncome.setCardExpdtAmt(0);
        computeIncome.setCashExpdtAmt(0);
        computeIncome.setCnsmpInclnCd("default");
        computeIncome.setYearIncome(0);

        // 현재 년도와 월
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();


        //월평균소득 ( 년 입금 총액/ 년월 입금되는 회차 )
      /*  i) count>=12  sum /12
          ii) count<12  sum / count
       */
        Long yearAvgIncome = queryFactory
                .select(Expressions.numberTemplate(Long.class, "COALESCE(SUM({0}) / NULLIF(COUNT(*), 0), 0)", apiAccountDetail.tranAmt))
                .from(apiAccountDetail, userAccount)
                .where(
                        userAccount.userSeq.userSeq.eq(Long.valueOf(userSeq)),
                        apiAccountDetail.inoutTypeCd.eq("I"),
                        userAccount.useYn.eq('Y'),
                        apiAccountDetail.accountNo.accountNo.eq(userAccount.accountNo),
                        apiAccountDetail.tranDate.year().eq(currentYear),
                        apiAccountDetail.tranDate.month().eq(currentMonth)
                )
                .fetchOne();

        Integer count = Math.toIntExact(queryFactory
                .select(apiAccountDetail.tranAmt.count())
                .from(apiAccountDetail, userAccount)
                .where(
                        userAccount.userSeq.userSeq.eq(Long.valueOf(userSeq)),
                        apiAccountDetail.inoutTypeCd.eq("I"),
                        userAccount.useYn.eq('Y'),
                        apiAccountDetail.accountNo.accountNo.eq(userAccount.accountNo),
                        apiAccountDetail.tranDate.year().eq(currentYear),
                        apiAccountDetail.tranDate.month().eq(currentMonth)
                )
                .fetchOne());

        if (count != null && count >= 12) {
            // count가 12 이상인 경우에만 12로 나눈 값을 저장 (월평균소득)
            yearAvgIncome = yearAvgIncome / 12;
        }else if (count>=1){
            yearAvgIncome = yearAvgIncome / count;
        }


        //연소득
        Long yearIncome = queryFactory
                .select(Expressions.numberTemplate(Long.class, "COALESCE({0}, {1})", apiAccountDetail.tranAmt.sum(), 0))
                .from(apiAccountDetail, userAccount)
                .where(
                        userAccount.userSeq.userSeq.eq(Long.valueOf(userSeq)),
                        apiAccountDetail.inoutTypeCd.eq("I"),
                        userAccount.useYn.eq('Y'),
                        apiAccountDetail.accountNo.accountNo.eq(userAccount.accountNo),
                        apiAccountDetail.tranDate.year().eq(currentYear),  // 현재 년도와
                        apiAccountDetail.tranDate.month().eq(currentMonth) // 현재 월에 해당하는 데이터만 조회
                )
                .fetchOne();


        //월지출
        Long cashAmt = queryFactory
                .select(Expressions.numberTemplate(Long.class, "COALESCE({0}, {1})", apiAccountDetail.tranAmt.sum(), 0))
                .from(apiAccountDetail, userAccount)
                .where(
                        userAccount.userSeq.userSeq.eq(Long.valueOf(userSeq)),
                        apiAccountDetail.inoutTypeCd.eq("O"),
                        userAccount.useYn.eq('Y'),
                        apiAccountDetail.accountNo.accountNo.eq(userAccount.accountNo),
                        apiAccountDetail.tranDate.year().eq(currentYear),  // 현재 년도와
                        apiAccountDetail.tranDate.month().eq(currentMonth) // 현재 월에 해당하는 데이터만 조회
                )
                .fetchFirst();
        
        //월예적금금액
        Long savingAmt = queryFactory
                .select(Expressions.numberTemplate(Long.class, "COALESCE({0}, {1})", apiAccountDetail.tranAmt.sum(), 0))
                .from(apiAccountDetail, userAccount)
                .where(
                        userAccount.userSeq.userSeq.eq(Long.valueOf(userSeq)),
                        apiAccountDetail.inoutTypeCd.eq("O"),
                        userAccount.useYn.eq('Y'),
                        apiAccountDetail.recvClientAccountNum.in(
                                JPAExpressions
                                        .select(userAccount.accountNo)
                                        .from(QUser.user)
                                        .join(userAccount).on(QUser.user.userSeq.eq(userAccount.userSeq.userSeq))
                                        .where(QUser.user.userSeq.eq(Long.valueOf(userSeq)))
                        ),
                        apiAccountDetail.accountNo.accountNo.eq(userAccount.accountNo),
                        apiAccountDetail.tranDate.year().eq(currentYear),  // 현재 년도와
                        apiAccountDetail.tranDate.month().eq(currentMonth) // 현재 월에 해당하는 데이터만 조회
                )
                .fetchFirst();



        computeIncome.setSavingExpdtAmt(Math.toIntExact(savingAmt));
        computeIncome.setCashExpdtAmt(Math.toIntExact(cashAmt));
        computeIncome.setYearIncome(Math.toIntExact(yearIncome));

        // 소득범위코드 설정
        setIncomeScopeCode(computeIncome);

        // 소비성향코드 설정
        setConsumptionInclinationCode(yearAvgIncome, computeIncome);

        // 소비성향 저장
        computeIncomeRepository.save(computeIncome);
    }
    
    // 소득범위코드
    private void setIncomeScopeCode(UserComuteIncome computeIncome) {

        Integer yearAvgIncome = computeIncome.getYearIncome();

        System.out.println("yearAvgIncome::: "+ yearAvgIncome);


        if (yearAvgIncome > 100000000) {
            computeIncome.setIncomeScopeCd("A");
        } else if (yearAvgIncome <= 100000000 && yearAvgIncome > 70000000) {
            computeIncome.setIncomeScopeCd("B");
        } else if (yearAvgIncome <= 70000000 && yearAvgIncome > 40000000) {
            computeIncome.setIncomeScopeCd("C");
        } else if (yearAvgIncome <= 40000000 && yearAvgIncome > 10000000) {
            computeIncome.setIncomeScopeCd("D");
        } else {
            computeIncome.setIncomeScopeCd("E");
        }
    }

    // 회원
    private void setConsumptionInclinationCode(Long yearAvgIncome, UserComuteIncome computeIncome) {

        //소득이 없으면 빈값저장
        if(yearAvgIncome == 0){
            computeIncome.setCnsmpInclnCd("");
            return;
        }

        // 과소비지수 = ( 월평균 수입 - 월평균 저축액 ) / 월평균 수입
        double ratio = ((double)(yearAvgIncome - safeAdd(computeIncome.getSavingExpdtAmt(), 0)) / yearAvgIncome);

        System.out.println("yearAvgIncome! :::::: "+yearAvgIncome);
        System.out.println("computeIncome.getSavingExpdtAmt()! :::::: "+computeIncome.getSavingExpdtAmt());
        System.out.println("ratio! :::::: "+ratio);

        // st_cnsmp_incln 테이블에서 disp_seq를 기준으로 오름차순으로 정렬하여 over_cnsmp_incln이 ratio 이하인 레코드 조회
        QConsumptionInclination qConsumptionInclination = QConsumptionInclination.consumptionInclination;
        ConsumptionInclination inclination = queryFactory
                .selectFrom(qConsumptionInclination)
                .where(qConsumptionInclination.overCnsmpIncln.goe(ratio))
                .orderBy(qConsumptionInclination.dispSeq.asc())
                .limit(1)
                .fetchOne();

        computeIncome.setCnsmpInclnCd(inclination.getCnsmpInclnCd());

    }

    private Integer safeAdd(Integer a, Integer b) {
        return (a != null ? a : 0) + (b != null ? b : 0);
    }

    // 비회원 (월저축액을 비율로 받아옴)
    @Transactional
    public String selectVisitorComputeIncome(Long yearIncome, Integer savingRatios) {
        // 연소득을 월 단위로 변환
        yearIncome = yearIncome / 12;

        // 비율을 0에서 1 사이의 소수로 변환
        double savingRatio = savingRatios / 100.0;

        // 과소비지수 = (월평균 수입 - 월평균 수입 * 저축비율) / 월평균 수입
        double ratio = ((double)(yearIncome - (yearIncome * savingRatio)) / yearIncome);

        System.out.println("yearAvgIncome! :::::: " + yearIncome);
        System.out.println("savingRatio! :::::: " + savingRatio);
        System.out.println("ratio! :::::: " + ratio);

        // st_cnsmp_incln 테이블에서 disp_seq를 기준으로 오름차순으로 정렬하여 over_cnsmp_incln이 ratio 이하인 레코드 조회
        QConsumptionInclination qConsumptionInclination = QConsumptionInclination.consumptionInclination;
        ConsumptionInclination inclination = queryFactory
                .selectFrom(qConsumptionInclination)
                .where(qConsumptionInclination.overCnsmpIncln.gt(ratio))
                .orderBy(qConsumptionInclination.dispSeq.asc())
                .limit(1)
                .fetchOne();

        System.out.println("selectVisitorComputeIncome getCnsmpInclnCd():::::: " + inclination.getCnsmpInclnCd());

        return inclination.getCnsmpInclnCd();
    }

}