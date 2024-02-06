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
    private Long approvalNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_no")
    @Comment("카드번호")
    private UserCard cardNo;

    @Column(name = "business_type")
    @Comment("가맹점사업자등록번호")
    private String businessType;

    @Column(name = "company_name")
    @Comment("가맹점명")
    private String companyName;

    @Column(name = "registration_number")
    @Comment("가맹점번호")
    private String registrationNumber;

    @Column(name = "frnc_idstr_cd")
    @Comment("거래금액")
    private String frncIdstrCd;

    @Column(name = "approval_amount")
    @Comment("승인일시")
    private Long approvalAmount;

    @Column(name = "approval_date")
    @Comment("할부개월")
    private LocalDateTime approvalDate;

    @Column(name = "installration_month")
    @Comment("가맹점업종코드")
    private Integer installrationMonth;

    @Builder
    public CardDetail(Long approvalNo, UserCard cardNo, String businessType, String companyName,
                      String registrationNumber, String frncIdstrCd, Long approvalAmount,
                      LocalDateTime approvalDate, Integer installrationMonth) {
        this.approvalNo = approvalNo;
        this.cardNo = cardNo;
        this.businessType = businessType;
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.frncIdstrCd = frncIdstrCd;
        this.approvalAmount = approvalAmount;
        this.approvalDate = approvalDate;
        this.installrationMonth = installrationMonth;
    }



}
