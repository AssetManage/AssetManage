package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity(name = "api_products")
@IdClass(ProductKeyId.class)
public class Product extends BaseDateTimeEntity {

    @Id
    @Column(name = "fin_co_no")
    private String finCoNo;

    @Id
    @Column(name = "fin_prdt_cd")
    private String finPrdtCd;

    @Id
    @Column(name = "dcls_month")
    private String dclsMonth;

    @Column(name = "act_kind_cd")
    private String actKindCd;

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


    @OneToMany(mappedBy = "product",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOption> productOptions;


    @Builder
    public Product(String finCoNo, String finPrdtCd, String dclsMonth, String actKindCd,
        String korCoNm,
        String finPrdtNm, String joinWay, String mtrtInt, String spclCnd, String joinDeny,
        String joinMember, String etcNote, Integer maxLmit, String dclsStrtDay, String dclsEndDay,
        String finCoSubmDay) {
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
        this.dclsMonth = dclsMonth;
        this.actKindCd = actKindCd;
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
    }
}
