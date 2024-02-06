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
@Entity(name ="api_card_detail")
public class CardDetail extends BaseDateTimeEntity {

    @Id
    @Column(name = "approval_no")
    @Comment("승인번호")
    private Integer approvalNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_no")
    @Comment("카드번호")
    private UserCard cardNo;

    @Column(name = "frnc_business_num")
    @Comment("가맹점사업자등록번호")
    private String frncBusinessNum;

    @Column(name = "frnc_num")
    @Comment("가맹점번호")
    private String frncNum;

    @Column(name = "frnc_nm")
    @Comment("가맹점명")
    private String frncNm;

    @Column(name = "frnc_idstr_cd")
    @Comment("가맹점업종코드")
    private String frncIdstrCd;

    @Column(name = "approval_amt")
    @Comment("거래금액")
    private Integer approvalAmt;

    @Column(name = "approval_date")
    @Comment("승인일시")
    private LocalDateTime approvalDate;

    @Column(name = "installration_month")
    @Comment("할부개월")
    private Integer installrationMonth;

    @Builder
    public CardDetail(Integer approvalNo, UserCard cardNo, String frncBusinessNum, String frncNum,
                      String frncNm, String frncIdstrCd, Integer approvalAmt,
                      LocalDateTime approvalDate, Integer installrationMonth) {
        this.approvalNo = approvalNo;
        this.cardNo = cardNo;
        this.frncBusinessNum = frncBusinessNum;
        this.frncNum = frncNum;
        this.frncNm = frncNm;
        this.frncIdstrCd = frncIdstrCd;
        this.approvalAmt = approvalAmt;
        this.approvalDate = approvalDate;
        this.installrationMonth = installrationMonth;
    }



}
