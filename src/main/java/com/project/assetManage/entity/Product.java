package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name ="products")
@IdClass(ProductKeyId.class)
public class Product extends BaseDateTimeEntity {

    @Id
    @Column(name="fin_co_no")
    private String finCoNo;

    @Id
    @Column(name="fin_prdt_cd")
    private String finPrdtCd;

    @Column(name="dcls_month")
    private String dclsMonth;

    @Column(name = "kor_co_nm")
    private String korCoNm;
    @Column(name = "fin_prdt_nm")
    private String finPrdtNm;
    @Column(name = "join_way")
    private String joinWay;
    @Column(name = "mtrt_int")
    private String mtrtInt;
    @Column(name = "spcl_cnd")
    private String spclCnd;
    @Column(name = "join_deny")
    private String joinDeny;
    @Column(name = "join_member")
    private String joinMember;
    @Column(name = "etc_note")
    private String etcNote;
    @Column(name = "max_limit")
    private Integer maxLmit;
    @Column(name = "dcls_strt_day")
    private String dclsStrtDay;
    @Column(name = "dcls_end_day")
    private String dclsEndDay;
}
