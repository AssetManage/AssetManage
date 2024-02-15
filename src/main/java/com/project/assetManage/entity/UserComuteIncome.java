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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    @Comment("회원번호")
    private User userSeq;

    @Column(name = "cnsmp_incln_cd")
    @Comment("소비성향코드")
    private String cnsmpInclnCd;

    @Column(name = "income_scope_cd")
    @Comment("소득범위코드")
    private String incomeScopeCd;

    @Column(name = "year_income")
    @Comment("연소득")
    private String yearIncome;

    @Column(name = "card_expdt_amt")
    @Comment("카드지출금액")
    private Character cardExpdtAmt;

    @Column(name = "cash_expdt_amt")
    @Comment("현금지출금액")
    private String cashExpdtAmt;

    @Column(name = "saving_expdt_amt")
    @Comment("예적금이체금액")
    private String savingExpdtAmt;

    @Builder
    public UserComuteIncome(User userSeq, String cnsmpInclnCd, String incomeScopeCd,
                            String yearIncome, Character cardExpdtAmt, String cashExpdtAmt, String savingExpdtAmt) {
        this.userSeq = userSeq;
        this.cnsmpInclnCd = cnsmpInclnCd;
        this.incomeScopeCd = incomeScopeCd;
        this.yearIncome = yearIncome;
        this.cardExpdtAmt = cardExpdtAmt;
        this.cashExpdtAmt = cashExpdtAmt;
        this.savingExpdtAmt = savingExpdtAmt;
    }

    public void setCashExpdtAmt(String s) {
    }

    public void setYearIncome(String s) {
    }

}


