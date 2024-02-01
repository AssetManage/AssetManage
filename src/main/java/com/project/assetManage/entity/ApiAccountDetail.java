package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_account_detail")
public class ApiAccountDetail extends BaseDateTimeEntity {

    @Id
    @Column(name = "bank_tran_id")
    private Long bankTranId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no")
    private ApiUserAccount accountNo;

    @Column(name = "tran_date")
    private LocalDate tranDate;

    @Column(name = "tran_time")
    private LocalTime tranTime;

    @Column(name = "inout_type_cd")
    private String inoutTypeCd;

    @Column(name = "printed_content")
    private String printedContent;

    @Column(name = "tran_amt")
    private Long tranAmt;

    @Column(name = "after_balance_amt")
    private Long afterBalanceAmt;

    @Column(name = "frnc_num")
    private String frncNum;

    @Column(name = "frnc_business_num")
    private String frncBusinessNum;

    @Column(name = "frnc_name")
    private String frncName;

    @Column(name = "frnc_idstr_cd")
    private String frncIdstrCd;

    @Builder
    public ApiAccountDetail(Long bankTranId, ApiUserAccount accountNo, LocalDate tranDate, LocalTime tranTime,
                            String inoutTypeCd, String printedContent, Long tranAmt, Long afterBalanceAmt,
                            String frncNum, String frncBusinessNum, String frncName, String frncIdstrCd) {
        this.bankTranId = bankTranId;
        this.accountNo = accountNo;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.inoutTypeCd = inoutTypeCd;
        this.printedContent = printedContent;
        this.tranAmt = tranAmt;
        this.afterBalanceAmt = afterBalanceAmt;
        this.frncNum = frncNum;
        this.frncBusinessNum = frncBusinessNum;
        this.frncName = frncName;
        this.frncIdstrCd = frncIdstrCd;
    }

}
