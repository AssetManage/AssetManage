package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_account_detail")
public class ApiAccountDetail extends BaseDateTimeEntity {

    @Id
    @Column(name = "bank_tran_id")
    private Long bank_tran_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no")
    private ApiUserAccount account_no;

    @Column(name = "tran_date")
    private LocalDate tran_date;

    @Column(name = "tran_time")
    private LocalTime tran_time;

    @Column(name = "inout_type_cd")
    private String inout_type_cd;

    @Column(name = "printed_content")
    private String printed_content;

    @Column(name = "tran_amt")
    private Long tran_amt;

    @Column(name = "after_balance_amt")
    private Long after_balance_amt;

    @Column(name = "frnc_num")
    private String frnc_num;

    @Column(name = "frnc_business_num")
    private String frnc_business_num;

    @Column(name = "frnc_name")
    private String frnc_name;

    @Column(name = "frnc_idstr_cd")
    private String frnc_idstr_cd;

}
