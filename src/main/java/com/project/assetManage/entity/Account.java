package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name ="account")
public class Account extends BaseDateTimeEntity {


    @Id
    @Column(name = "account_num")
    private String accountNum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "account_name")
    private String accountName;
    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_kind")
    private String accountKind;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "rate")
    private Double rate;

}
