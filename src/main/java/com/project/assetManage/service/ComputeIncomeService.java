package com.project.assetManage.service;

import org.springframework.stereotype.Service;

@Service
public class ComputeIncomeService{
/*
    private ComputeIncomeRepository computeIncomeRepository;
    private UserAccountRepository userAccountRepository;
    private final ApiAccountDetailRepository apiAccountDetailRepository;
    private final JPAQueryFactory queryFactory;

    public ComputeIncomeService(ComputeIncomeRepository computeIncomeRepository, UserAccountRepository userAccountRepository, ApiAccountDetailRepository apiAccountDetailRepository, EntityManager entityManager) {
        this.computeIncomeRepository = computeIncomeRepository;
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
                .where(userAccount.userSeq.userSeq.eq(userSeq))
                .fetch();

        // 산출소득 초기화
        UserComuteIncome computeIncome = new UserComuteIncome();
      *//* computeIncome.setUserSeq(userSeq);
        computeIncome.setCardExpdtAmt("0");
        computeIncome.setCashExpdtAmt("0");
        computeIncome.setCnsmpInclnCd("default");
        computeIncome.setIncomeScopeCd("default");
        computeIncome.setMonthIncome(0);
        computeIncome.setSavingExpdtAmt("0");
        computeIncome.setYearIncome("0");*//*

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

                // 거래금액 합산
                if ("O".equals(inoutTypeCd)) {
                    computeIncome.setCashExpdtAmt(String.valueOf(Integer.parseInt(computeIncome.getCashExpdtAmt()) + tranAmt));
                } else if ("I".equals(inoutTypeCd)) {
                    computeIncome.setYearIncome(String.valueOf(Integer.parseInt(computeIncome.getYearIncome()) + tranAmt));
                }
            }
        }

        // 계산된 소비성향 정보 저장
        computeIncomeRepository.save(computeIncome);
    }

    public void updateComputeIncome() {

        // computeIncomeRepository.update(...) 등
    }*/
}


