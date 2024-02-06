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
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "api_products")
@IdClass(ProductKeyId.class)
public class Product extends BaseDateTimeEntity {

    @Id
    @Column(name = "fin_co_no")
    @Comment("금융회사코드")
    private String finCoNo;

    @Id
    @Column(name = "fin_prdt_cd")
    @Comment("금융상품코드")
    private String finPrdtCd;

    @Id
    @Column(name = "dcls_month")
    @Comment("공시제출월[YYYYMM]")
    private String dclsMonth;

    @Column(name = "kor_co_nm")
    @Comment("금융회사명")
    private String korCoNm;

    @Column(name = "fin_prdt_nm")
    @Comment("금융상품명")
    private String finPrdtNm;

    @Column(name = "join_way")
    @Comment("가입방법")
    private String joinWay;

    @Column(name = "mtrt_int")
    @Comment("만기후이자율")
    private String mtrtInt;

    @Column(name = "spcl_cnd")
    @Comment("우대조건")
    private String spclCnd;

    @Column(name = "join_deny")
    @Comment("가입제한")
    private String joinDeny;

    @Column(name = "join_member")
    @Comment("가입대상")
    private String joinMember;

    @Column(name = "etc_note")
    @Comment("기타유의사항")
    private String etcNote;

    @Column(name = "max_limit", nullable = true)
    @Comment("최고한도")
    private Integer maxLmit;

    @Column(name = "dcls_strt_day")
    @Comment("공시시작일")
    private String dclsStrtDay;

    @Column(name = "dcls_end_day")
    @Comment("공시종료일")
    private String dclsEndDay;

    @Column(name = "fin_co_subm_day")
    @Comment("금융회사제출일")
    private String finCoSubmDay;

    @Column(name = "act_kind_cd")
    @Comment("상품분류코드")
    private String actKindCd;

    @Column(name = "join_way_cd")
    @Comment("가입방법코드")
    private String joinWayCd;


    @OneToMany(mappedBy = "product",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOption> productOptions;


    @Builder
    public Product(String finCoNo, String finPrdtCd, String dclsMonth, String actKindCd,
        String korCoNm,
        String finPrdtNm, String joinWay, String mtrtInt, String spclCnd, String joinDeny,
        String joinMember, String etcNote, Integer maxLmit, String dclsStrtDay, String dclsEndDay,
        String finCoSubmDay, String joinWayCd) {
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
        this.joinWayCd = joinWayCd;
    }
}
