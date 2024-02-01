package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_card")
public class ApiUserCard extends BaseDateTimeEntity {

    @Id
    @Column(name = "card_no")
    private Long cardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private Member userSeq;

    @Column(name = "card_nm")
    private String cardNm;

    @Column(name = "card_co_no")
    private String cardCoNo;

    @Column(name = "card_pw")
    private String cardPw;

    @Column(name = "valid_thru_year", columnDefinition = "char(2)")
    private Character validThruYear;

    @Column(name = "valid_thru_month", columnDefinition = "char(2)")
    private Character validThruMonth;

    @Column(name = "cvc_cd", columnDefinition = "char(3)")
    private Character cvcCd;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "user_yn")
    private Character userYn;

    @Builder
    public ApiUserCard(Long cardNo, Member userSeq, String cardNm, String cardCoNo, String cardPw,
                      Character validThruYear, Character validThruMonth, Character cvcCd,
                      LocalDateTime joinDate, Character userYn) {
        this.cardNo = cardNo;
        this.userSeq = userSeq;
        this.cardNm = cardNm;
        this.cardCoNo = cardCoNo;
        this.cardPw = cardPw;
        this.validThruYear = validThruYear;
        this.validThruMonth = validThruMonth;
        this.cvcCd = cvcCd;
        this.joinDate = joinDate;
        this.userYn = userYn;
    }
}
