package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_card_detail")
public class ApiCardDetail extends BaseDateTimeEntity {

    @Id
    @Column(name = "approval_no")
    private Long approvalNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_no")
    private ApiUserCard cardNo;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "frnc_idstr_cd")
    private String frncIdstrCd;

    @Column(name = "approval_amount")
    private Long approvalAmount;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @Column(name = "installration_month")
    private Integer installrationMonth;

    @Builder
    public ApiCardDetail(Long approvalNo, ApiUserCard cardNo, String businessType, String companyName,
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
