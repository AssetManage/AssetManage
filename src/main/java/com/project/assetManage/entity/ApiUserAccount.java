package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_account")
public class ApiUserAccount extends BaseDateTimeEntity {

    @Id
    @Column(name = "account_no")
    private String accountNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User userSeq;

    @Column(name = "fin_prdt_cd")
    private String finPrdtCd;

    @Column(name = "fin_co_no")
    private String finCoNo;

    @Column(name = "dcls_month")
    private LocalDate dclsMonth;

    @Column(name = "account_seq")
    private Integer accountSeq;

    @Column(name = "account_issue_date")
    private LocalDateTime accountIssueDate;

    @Column(name = "maturity_date")
    private LocalDateTime maturityDate;

    @Column(name = "last_tran_date")
    private LocalDateTime lastTranDate;

    @Column(name = "product_nm")
    private String productNm;

    @Column(name = "product_sub_name")
    private String productSubName;

    @Column(name = "balance_amt")
    private Long balanceAmt;

    @Column(name = "use_yn")
    private Character useYn;

    @Builder
    public ApiUserAccount(String accountNo, User userSeq, String finPrdtCd, String finCoNo,
                          LocalDate dclsMonth, Integer accountSeq, LocalDateTime accountIssueDate,
                          LocalDateTime maturityDate, LocalDateTime lastTranDate, String productNm,
                          String productSubName, Long balanceAmt, Character useYn) {
        this.accountNo = accountNo;
        this.userSeq = userSeq;
        this.finPrdtCd = finPrdtCd;
        this.finCoNo = finCoNo;
        this.dclsMonth = dclsMonth;
        this.accountSeq = accountSeq;
        this.accountIssueDate = accountIssueDate;
        this.maturityDate = maturityDate;
        this.lastTranDate = lastTranDate;
        this.productNm = productNm;
        this.productSubName = productSubName;
        this.balanceAmt = balanceAmt;
        this.useYn = useYn;
    }


}
