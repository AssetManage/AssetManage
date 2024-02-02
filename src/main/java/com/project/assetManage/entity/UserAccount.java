package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_account")
public class UserAccount extends BaseDateTimeEntity {

    @Id
    @Column(name = "account_num")
    private String accountNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User userSeq;

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
