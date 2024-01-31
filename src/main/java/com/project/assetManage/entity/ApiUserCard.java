package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name ="api_user_card")
public class ApiUserCard extends BaseDateTimeEntity {

    @Id
    @Column(name = "card_no")
    private Long approval_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private Member seq;

    @Column(name = "card_nm")
    private String card_nm;

    @Column(name = "card_co_no")
    private String card_co_no;

    @Column(name = "card_pw")
    private String card_pw;

    @Column(name = "valid_thru_year", columnDefinition = "char(2)")
    private Character valid_thru_year;

    @Column(name = "valid_thru_month", columnDefinition = "char(2)")
    private Character valid_thru_month;

    @Column(name = "cvc_cd", columnDefinition = "char(3)")
    private Character cvc_cd;

    @Column(name = "join_date")
    private LocalDateTime join_date;

    @Column(name = "user_yn")
    private Character user_yn;

}
