package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_card")
public class UserCard extends BaseDateTimeEntity {

    @Id
    @Column(name = "card_no")
    @Comment("카드번호")
    private Long cardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    @Comment("회원번호")
    private User userSeq;

    @Column(name = "card_nm")
    @Comment("카드명칭")
    private String cardNm;

    @Column(name = "card_co_no")
    @Comment("카드사코드")
    private String cardCoNo;

    @Column(name = "card_pw")
    @Comment("카드비밀번호")
    private String cardPw;

    @Column(name = "valid_thru_year", columnDefinition = "char(2)")
    @Comment("유효기간(연)")
    private Character validThruYear;

    @Column(name = "valid_thru_month", columnDefinition = "char(2)")
    @Comment("유효기간(월)")
    private Character validThruMonth;

    @Column(name = "cvc_cd", columnDefinition = "char(3)")
    @Comment("cvc코드")
    private Character cvcCd;

    @Column(name = "join_date")
    @Comment("가입일시")
    private LocalDateTime joinDate;

    @Column(name = "user_yn")
    @Comment("사용여부")
    private Character userYn;

    @Builder
    public UserCard(Long cardNo, User userSeq, String cardNm, String cardCoNo, String cardPw,
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
