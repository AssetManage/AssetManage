package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_account")
public class ApiUserAccount extends BaseDateTimeEntity {

    @Id
    @Column(name = "account_no")
    private String account_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private Member seq;

    @Column(name = "fin_prdt_cd")
    private String fin_prdt_cd;

    @Column(name = "fin_co_no")
    private String fin_co_no;

    @Column(name = "dcls_month")
    private LocalDate dcls_month;

    @Column(name = "account_seq")
    private Integer account_seq;

    @Column(name = "account_issue_date")
    private LocalDateTime account_issue_date;

    @Column(name = "maturity_date")
    private LocalDateTime maturity_date;

    @Column(name = "last_tran_date")
    private LocalDateTime last_tran_date;

    @Column(name = "product_nm")
    private String product_nm;

    @Column(name = "product_sub_name")
    private String product_sub_name;

    @Column(name = "balance_amt")
    private Long balance_amt;

    @Column(name = "use_yn")
    private Character use_yn;


}
