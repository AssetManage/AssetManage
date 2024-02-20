package com.project.assetManage.service;

import com.project.assetManage.entity.QAccountDetail;
import com.project.assetManage.entity.QUserAccount;
import com.project.assetManage.entity.UserAccount;
import com.project.assetManage.entity.UserComuteIncome;
import com.project.assetManage.entity.common.Code;
import com.project.assetManage.repository.ApiAccountDetailRepository;
import com.project.assetManage.repository.CodeRepository;
import com.project.assetManage.repository.ComputeIncomeRepository;
import com.project.assetManage.repository.UserAccountRepository;
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
    private UserAccountRepository userAccountRepository;
    private final ApiAccountDetailRepository apiAccountDetailRepository;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ComputeIncomeService(ComputeIncomeRepository computeIncomeRepository, UserAccountRepository userAccountRepository, ApiAccountDetailRepository apiAccountDetailRepository, EntityManager entityManager,  CodeRepository codeRepository) {
        this.computeIncomeRepository = computeIncomeRepository;
        this.codeRepository = codeRepository;
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
        computeIncome.setUserSeq(userSeq);
        computeIncome.setCardExpdtAmt(0);
        computeIncome.setCashExpdtAmt(0);
        computeIncome.setCnsmpInclnCd("default");
        //computeIncome.setIncomeScopeCd("default");
        computeIncome.setSavingExpdtAmt(0);
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

                // 거래금액 합산
                if ("O".equals(inoutTypeCd)) {
                    computeIncome.setCashExpdtAmt(computeIncome.getCashExpdtAmt() + tranAmt);
                } else if ("I".equals(inoutTypeCd)) {
                    computeIncome.setYearIncome(computeIncome.getYearIncome() + tranAmt);
                }
            }
        }
        // 소득범위 코드 설정
        setIncomeScopeCode(computeIncome);

        // 계산된 소비성향 정보 저장
        computeIncomeRepository.save(computeIncome);
    }

    private void setIncomeScopeCode(UserComuteIncome computeIncome) {
        // st_code 테이블에서 소득범위 코드 조회
        Optional<Code> incomeScopeCodeOptional = codeRepository.findByGroupCodeAndCodeId("income_scope_cd", computeIncome.getIncomeScopeCd());

        incomeScopeCodeOptional.ifPresent(incomeScopeCode -> {
            // st_code에서 조회한 값으로 소득범위 코드 설정
            switch (incomeScopeCode.getCodeId()) {
                case "A":
                    computeIncome.setIncomeScopeCd("1억 초과");
                    break;
                case "B":
                    computeIncome.setIncomeScopeCd("1억 이하");
                    break;
                case "C":
                    computeIncome.setIncomeScopeCd("7000만원 이하");
                    break;
                case "D":
                    computeIncome.setIncomeScopeCd("4000만원 이하");
                    break;
                case "E":
                    computeIncome.setIncomeScopeCd("1000만원 이하");
                    break;
                default:
                    // 기본값 설정 또는 예외 처리
                    computeIncome.setIncomeScopeCd("기본값");
            }
        });
    }

    public void updateComputeIncome() {

        // computeIncomeRepository.update(...) 등
    }
}


