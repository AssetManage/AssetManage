package com.project.assetManage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "st_user_compute_income")
public class UserComuteIncome {

    @Id
/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")*/
    @Comment("회원번호")
    private String userSeq;

    @Column(name = "cnsmp_incln_cd")
    @Comment("소비성향코드")
    private String cnsmpInclnCd;

    @Column(name = "income_scope_cd")
    @Comment("소득범위코드")
    private String incomeScopeCd;

    @Column(name = "year_income")
    @Comment("연소득")
    private Integer yearIncome;

    @Column(name = "card_expdt_amt")
    @Comment("카드지출금액")
    private Integer cardExpdtAmt;

    @Column(name = "cash_expdt_amt")
    @Comment("현금지출금액")
    private Integer cashExpdtAmt;

    @Column(name = "saving_expdt_amt")
    @Comment("예적금이체금액")
    private Integer savingExpdtAmt;

    @Column(name = "recv_client_name")
    @Comment("최종수취고객성명")
    private String recvClientName;

    @Column(name = "recv_client_bank_code")
    @Comment("최종수취고객계좌 금융회사코드")
    private String recvClientBankCode;

    @Column(name = "recv_client_account_num")
    @Comment("최종수취고객계좌번호")
    private String recvClientAccountNum;

    // Getter methods
    public String getUserSeq() {
        return userSeq;
    }

    public String getCnsmpInclnCd() {
        return cnsmpInclnCd;
    }

    public String getIncomeScopeCd() {
        return incomeScopeCd;
    }

    public Integer getYearIncome() {
        return yearIncome;
    }

    public Integer getCardExpdtAmt() {
        return cardExpdtAmt;
    }

    public Integer getCashExpdtAmt() {
        return cashExpdtAmt;
    }

    public Integer getSavingExpdtAmt() {
        return savingExpdtAmt;
    }

    public String getRecvClientName() {
        return recvClientName;
    }

    public String getRecvClientBankCode() {
        return recvClientBankCode;
    }

    public String getRecvClientAccountNum() {
        return recvClientAccountNum;
    }

    // Setter methods
    public void setUserSeq(String userSeq) {
        this.userSeq = userSeq;
    }

    public void setCnsmpInclnCd(String cnsmpInclnCd) {
        this.cnsmpInclnCd = cnsmpInclnCd;
    }

    public void setIncomeScopeCd(String incomeScopeCd) {
        this.incomeScopeCd = incomeScopeCd;
    }

    public void setYearIncome(Integer yearIncome) {
        this.yearIncome = yearIncome;
    }

    public void setCardExpdtAmt(Integer cardExpdtAmt) {
        this.cardExpdtAmt = cardExpdtAmt;
    }

    public void setCashExpdtAmt(Integer cashExpdtAmt) {
        this.cashExpdtAmt = cashExpdtAmt;
    }

    public void setSavingExpdtAmt(Integer savingExpdtAmt) {
        this.savingExpdtAmt = savingExpdtAmt;
    }

    public void setRecvClientName(String recvClientName) {
        this.recvClientName = recvClientName;
    }

    public void setRecvClientBankCode(String recvClientBankCode) {
        this.recvClientBankCode = recvClientBankCode;
    }

    public void setRecvClientAccountNum(String recvClientAccountNum) {
        this.recvClientAccountNum = recvClientAccountNum;
    }


    @Builder
    public UserComuteIncome(String userSeq, String cnsmpInclnCd, String incomeScopeCd,
                            Integer yearIncome, Integer cardExpdtAmt, Integer cashExpdtAmt, Integer savingExpdtAmt, String recvClientName, String recvClientBankCode, String recvClientAccountNum) {
        this.userSeq = userSeq;
        this.cnsmpInclnCd = cnsmpInclnCd;
        this.incomeScopeCd = incomeScopeCd;
        this.yearIncome = yearIncome;
        this.cardExpdtAmt = cardExpdtAmt;
        this.cashExpdtAmt = cashExpdtAmt;
        this.savingExpdtAmt = savingExpdtAmt;
        this.recvClientName = recvClientName;
        this.recvClientBankCode = recvClientBankCode;
        this.recvClientAccountNum = recvClientAccountNum;
    }

}


