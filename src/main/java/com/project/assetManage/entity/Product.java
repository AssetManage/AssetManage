package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity(name = "products")
@IdClass(ProductKeyId.class)
public class Product extends BaseDateTimeEntity {

    @Id
    @Column(name = "fin_co_no")
    private String finCoNo;

    @Id
    @Column(name = "fin_prdt_cd")
    private String finPrdtCd;

    @Column(name = "act_kind_cd")
    private String actKindCd;

    @Column(name = "dcls_month")
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

    @Column(name = "max_limit", nullable = true)
    private Integer maxLmit;

    @Column(name = "dcls_strt_day")
    private String dclsStrtDay;

    @Column(name = "dcls_end_day")
    private String dclsEndDay;

    @Column(name = "fin_co_subm_day")
    private String finCoSubmDay;

    @Column(name = "intr_rate_type")
    private String intrRateType;

    @Column(name = "intr_rate_type_nm")
    private String intrRateTypeNm;

    @Column(name = "save_trm")
    private String saveTrm;

    @Column(name = "intr_rate")
    private Double intrRate;

    @Column(name = "intr_rate2")
    private Double intrRate2;

    @Column(name = "rsrv_type", nullable = true)
    private String rsrvType;

    @Column(name = "rsrv_type_nm", nullable = true)
    private String rsrvTypeNm;


    @Builder
    public Product(String finCoNo, String finPrdtCd, String actKindCd, String dclsMonth,
        String korCoNm, String finPrdtNm, String joinWay, String mtrtInt, String spclCnd,
        String joinDeny, String joinMember, String etcNote, Integer maxLmit, String dclsStrtDay,
        String dclsEndDay, String finCoSubmDay, String intrRateType, String intrRateTypeNm,
        String saveTrm, Double intrRate, Double intrRate2, String rsrvType, String rsrvTypeNm) {
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
        this.actKindCd = actKindCd;
        this.dclsMonth = dclsMonth;
        this.korCoNm = korCoNm;
        this.finPrdtNm = finPrdtNm;
        this.joinWay = joinWay;
        this.mtrtInt = mtrtInt;
        this.spclCnd = spclCnd;
        this.joinDeny = joinDeny;
        this.joinMember = joinMember;
        this.etcNote = etcNote;
        this.maxLmit = maxLmit;
        this.dclsStrtDay = dclsStrtDay;
        this.dclsEndDay = dclsEndDay;
        this.finCoSubmDay = finCoSubmDay;
        this.intrRateType = intrRateType;
        this.intrRateTypeNm = intrRateTypeNm;
        this.saveTrm = saveTrm;
        this.intrRate = intrRate;
        this.intrRate2 = intrRate2;
        this.rsrvType = rsrvType;
        this.rsrvTypeNm = rsrvTypeNm;
    }
}
