package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "transaction_history")
@IdClass(TransactionHistoryId.class)
public class TransactionHistory extends BaseDateTimeEntity {

    @Id
    @Column(name="card_id")
    private String cardId;

    @Id
    @Column(name="approval_num")
    private String approvalNum;

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
    @Column(name="approval_amount")
    private Long approvalAmount;
    @Column(name = "installments")
    private Integer installments;

    @Builder
    public TransactionHistory(String cardId, String approvalNum, Member member, String companyCategory, String companyAddress, String companyName, String companyRegistrationNum, LocalDateTime approvedDateTime, Long approvalAmount, Integer installments) {
        this.cardId = cardId;
        this.approvalNum = approvalNum;
        this.member = member;
        this.companyCategory = companyCategory;
        this.companyAddress = companyAddress;
        this.companyName = companyName;
        this.companyRegistrationNum = companyRegistrationNum;
        this.approvedDateTime = approvedDateTime;
        this.approvalAmount = approvalAmount;
        this.installments = installments;
    }
}
