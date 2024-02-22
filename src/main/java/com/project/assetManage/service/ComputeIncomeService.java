package com.project.assetManage.service;

import com.project.assetManage.entity.QAccountDetail;
import com.project.assetManage.entity.QUserAccount;
import com.project.assetManage.entity.UserAccount;
import com.project.assetManage.entity.UserComuteIncome;
import com.project.assetManage.entity.common.Code;
import com.project.assetManage.entity.common.ConsumptionInclination;
import com.project.assetManage.entity.common.QCode;
import com.project.assetManage.entity.common.QConsumptionInclination;
import com.project.assetManage.repository.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComputeIncomeService{

    private ComputeIncomeRepository computeIncomeRepository;
    private final CodeRepository codeRepository;
    private final ConsumptionInclinationRepository ConsumptionInclinationRepository;
    private UserAccountRepository userAccountRepository;
    private final ApiAccountDetailRepository apiAccountDetailRepository;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ComputeIncomeService(ComputeIncomeRepository computeIncomeRepository, UserAccountRepository userAccountRepository, ApiAccountDetailRepository apiAccountDetailRepository,
                                EntityManager entityManager,  CodeRepository codeRepository, ConsumptionInclinationRepository consumptionInclinationRepository) {
        this.computeIncomeRepository = computeIncomeRepository;
        this.codeRepository = codeRepository;
        this.ConsumptionInclinationRepository = consumptionInclinationRepository;
        this.userAccountRepository = userAccountRepository;
        this.apiAccountDetailRepository = apiAccountDetailRepository;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Transactional
    public void insertComputeIncome(String userSeq) {
        // QueryDSL 사용하여 계좌 정보 조회
        QUserAccount userAccount = QUserAccount.userAccount;
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
        //computeIncome.setIncomeScopeCd("default");
        //computeIncome.setSavingExpdtAmt(0);
        computeIncome.setYearIncome(0);


        // 계좌별 거래금액 합산
        QAccountDetail apiAccountDetail = QAccountDetail.accountDetail;
        for (UserAccount userAccountEntity : userAccounts) {
            List<Tuple> transactions = queryFactory
                    .select(apiAccountDetail.inoutTypeCd, apiAccountDetail.tranAmt)
                    .from(apiAccountDetail)
                    .where(apiAccountDetail.accountNo.accountNo.eq(userAccountEntity.getAccountNo()))
                    .fetch();

            for (Tuple transaction : transactions) {
                String inoutTypeCd = transaction.get(apiAccountDetail.inoutTypeCd);
                Integer tranAmt = transaction.get(apiAccountDetail.tranAmt);
                Integer savingAmt = 0;

                // 거래금액 합산
                if ("O".equals(inoutTypeCd)) {
                    QUserAccount apiUserAccount = QUserAccount.userAccount;

                    // 같은 user_seq를 가진 데이터들 중에 account_no가 있다면
                    boolean hasSavingAccount = queryFactory
                            .select(apiUserAccount.accountNo)
                            .from(apiUserAccount)
                            .where(apiUserAccount.userSeq.userSeq.eq(Long.valueOf(userSeq))
                                    .and(apiUserAccount.accountNo.ne(userAccountEntity.getAccountNo())))
                            .fetch()
                            .size() > 0;

                    //예적금이체금액, 현금지출금액
                    if (hasSavingAccount) {
                        computeIncome.setSavingExpdtAmt(computeIncome.getCashExpdtAmt() + savingAmt);
                    }else{
                        computeIncome.setCashExpdtAmt(computeIncome.getCashExpdtAmt() + tranAmt);
                    }

                } else if ("I".equals(inoutTypeCd)) {
                    computeIncome.setYearIncome(computeIncome.getYearIncome() + tranAmt);
                }
            }
        }
        // 소득범위코드 설정
        setIncomeScopeCode(computeIncome);

        // 소비성향코드 설정
        setConsumptionInclinationCode(computeIncome);

        // 소비성향 저장
        computeIncomeRepository.save(computeIncome);
    }

    private void setIncomeScopeCode(UserComuteIncome computeIncome) {
        // st_code 테이블에서 소득범위 코드 조회
        Optional<Code> incomeScopeCodeOptional = codeRepository.findByGroupCodeAndCodeId("income_scope_cd", computeIncome.getIncomeScopeCd());

        incomeScopeCodeOptional.ifPresent(incomeScopeCode -> {
            // st_code에서 조회한 값으로 소득범위 코드 설정
            int yearIncome = computeIncome.getYearIncome();

            if (yearIncome > 100000000) {
                computeIncome.setIncomeScopeCd("A");
            } else if (yearIncome <= 100000000 && yearIncome > 70000000) {
                computeIncome.setIncomeScopeCd("B");
            } else if (yearIncome <= 70000000 && yearIncome > 40000000) {
                computeIncome.setIncomeScopeCd("C");
            } else if (yearIncome <= 40000000 && yearIncome > 10000000) {
                computeIncome.setIncomeScopeCd("D");
            } else {
                computeIncome.setIncomeScopeCd("E");
            }
        });
    }

    private void setConsumptionInclinationCode(UserComuteIncome computeIncome) {
        // 계산된 ratio 값
        double ratio = ((double) computeIncome.getYearIncome() / 12 - computeIncome.getSavingExpdtAmt()) / (double) (computeIncome.getYearIncome() / 12);

        // st_cnsmp_incln 테이블에서 disp_seq를 기준으로 오름차순으로 정렬하여 over_cnsmp_incln이 ratio 이하인 레코드 조회
        QConsumptionInclination qConsumptionInclination = QConsumptionInclination.consumptionInclination;
        List<ConsumptionInclination> inclinations = queryFactory
                .selectFrom(qConsumptionInclination)
                .where(qConsumptionInclination.overCnsmpIncln.loe(ratio))
                .orderBy(qConsumptionInclination.dispSeq.asc())
                .fetch();

        // 조회된 레코드들을 확인하면서 cnsmp_incln_cd 값을 찾음
        for (ConsumptionInclination inclination : inclinations) {
            if (ratio <= Double.valueOf(inclination.getOverCnsmpIncln())) {
                computeIncome.setCnsmpInclnCd(inclination.getCnsmpInclnCd());
                break;
            }
        }
    }

    public void updateComputeIncome() {

        // computeIncomeRepository.update(...) 등
    }
}