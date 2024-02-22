package com.project.assetManage.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_account")
public class UserAccount  {

    @Id
    @Column(name = "account_no")
    @Comment("계좌번호")
    private String accountNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    @Comment("회원번호")
    private User userSeq;

    @Column(name = "fin_prdt_cd")
    @Comment("금융상품코드")
    private String finPrdtCd;

    @Column(name = "fin_co_no")
    @Comment("금융회사코드")
    private String finCoNo;

    @Column(name = "dcls_month")
    @Comment("공시제출월(YYYYMM)")
    private String dclsMonth;

    @Column(name = "account_seq")
    @Comment("회차번호")
    private Integer accountSeq;

    @Column(name = "account_issue_date")
    @Comment("계좌개설일")
    private LocalDateTime accountIssueDate;

    @Column(name = "maturity_date")
    @Comment("만기일")
    private LocalDateTime maturityDate;

    @Column(name = "last_tran_date")
    @Comment("최종거래일")
    private LocalDateTime lastTranDate;

    @Column(name = "product_nm")
    @Comment("상품명(계좌명)")
    private String productNm;

    @Column(name = "product_sub_name")
    @Comment("부기명")
    private String productSubName;

    @Column(name = "balance_amt")
    @Comment("계좌잔액")
    private Integer balanceAmt;

    @Column(name = "use_yn")
    @Comment("사용여부")
    private Character useYn;

    @Builder
    public UserAccount(String accountNo, User userSeq, String finPrdtCd, String finCoNo,
                       String dclsMonth, Integer accountSeq, LocalDateTime accountIssueDate,
                       LocalDateTime maturityDate, LocalDateTime lastTranDate, String productNm,
                       String productSubName, Integer balanceAmt, Character useYn) {
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
