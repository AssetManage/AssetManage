package com.project.assetManage.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_account_detail")
public class AccountDetail {

    @Id
    @Column(name = "bank_tran_id")
    @Comment("은행거래고유번호")
    private String bankTranId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no")
    @Comment("계좌번호")
    private UserAccount accountNo;

    @Column(name = "tran_date")
    @Comment("거래일자")
    private LocalDate tranDate;

    @Column(name = "tran_time")
    @Comment("거래시간")
    private LocalTime tranTime;

    @Column(name = "inout_type_cd")
    @Comment("입출금구분코드")
    private String inoutTypeCd;

    @Column(name = "printed_content")
    @Comment("통장인자내용")
    private String printedContent;

    @Column(name = "tran_amt")
    @Comment("거래금액")
    private Integer tranAmt;

    @Column(name = "after_balance_amt")
    @Comment("거래후잔액")
    private Integer afterBalanceAmt;

    @Column(name = "frnc_num")
    @Comment("가맹점번호")
    private String frncNum;

    @Column(name = "frnc_business_num")
    @Comment("가맹점사업자등록번호")
    private String frncBusinessNum;

    @Column(name = "frnc_name")
    @Comment("가맹점명")
    private String frncName;

    @Column(name = "frnc_idstr_cd")
    @Comment("가맹점업종코드")
    private String frncIdstrCd;

    @Column(name = "recv_client_account_num")
    @Comment("최종수취고객계좌번호")
    private String recvClientAccountNum;

    @Builder
    public AccountDetail(String bankTranId, UserAccount accountNo, LocalDate tranDate, LocalTime tranTime,
                         String inoutTypeCd, String printedContent, Integer tranAmt, Integer afterBalanceAmt,
                         String frncNum, String frncBusinessNum, String frncName, String frncIdstrCd, String recv_client_account_num) {
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
        this.recvClientAccountNum = recv_client_account_num;
    }

}
