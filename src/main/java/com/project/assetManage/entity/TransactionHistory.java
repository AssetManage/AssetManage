package com.project.assetManage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "transaction_history")
public class TransactionHistory {

    @Column(name="card_id")
    private String cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name="company_category")
    private String companyCategory;
    @Column(name="company_address")
    private String companyAddress;
    @Column(name="company_name")
    private String companyName;
    @Column(name="company_registration_num")
    private String companyRegistrationNum;
    @Column(name="approved_datetime")
    private LocalDateTime approvedDateTime;
    @Column(name="approval_num")
    private Long approvalNum;
    @Column(name = "installments")
    private Integer installments;


}
