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
    private Long userSeq;

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
    @Comment("월현금지출금액")
    private Integer cashExpdtAmt;

    @Column(name = "saving_expdt_amt")
    @Comment("월예적금이체금액")
    private Integer savingExpdtAmt;



    // Getter methods
    public Long getUserSeq() {
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


    // Setter methods
    public void setUserSeq(Long userSeq) {
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


    @Builder
    public UserComuteIncome(Long userSeq, String cnsmpInclnCd, String incomeScopeCd,
                            Integer yearIncome, Integer cardExpdtAmt, Integer cashExpdtAmt, Integer savingExpdtAmt) {
        this.userSeq = userSeq;
        this.cnsmpInclnCd = cnsmpInclnCd;
        this.incomeScopeCd = incomeScopeCd;
        this.yearIncome = yearIncome;
        this.cardExpdtAmt = cardExpdtAmt;
        this.cashExpdtAmt = cashExpdtAmt;
        this.savingExpdtAmt = savingExpdtAmt;

    }

}


