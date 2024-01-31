package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_card_detail")
public class ApiCardDetail extends BaseDateTimeEntity {

    @Id
    @Column(name = "approval_no")
    private Long approval_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_no")
    private ApiAccountDetail card_no;

    @Column(name = "business_type")
    private String business_type;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "registration_number")
    private String registration_number;

    @Column(name = "frnc_idstr_cd")
    private String frnc_idstr_cd;

    @Column(name = "approval_amount")
    private Long approval_amount;

    @Column(name = "approval_date")
    private LocalDateTime approval_date;

    @Column(name = "installration_month")
    private Integer installration_month;



}
