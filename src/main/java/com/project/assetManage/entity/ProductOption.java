package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "api_products_option")
@IdClass(ProductOptionKeyId.class)
public class ProductOption extends BaseDateTimeEntity {

    @Id
    @Column(name = "prd_option_seq")
    @Comment("상품옵션순번")
    private Long prdOptionSeq;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "fin_co_no", referencedColumnName = "fin_co_no",insertable = false, updatable = false),
            @JoinColumn(name = "fin_prdt_cd", referencedColumnName = "fin_prdt_cd", insertable = false, updatable = false),
            @JoinColumn(name = "dcls_month", referencedColumnName = "dcls_month", insertable = false, updatable = false)
    })
    private Product product;

    @Column(name = "intr_rate_type")
    @Comment("저축금리유형")
    private String intrRateType;

    @Column(name = "intr_rate_type_nm")
    @Comment("저축금리유형명")
    private String intrRateTypeNm;

    @Column(name = "save_trm")
    @Comment("저축기간")
    private Integer saveTrm;

    @Column(name = "intr_rate")
    @Comment("저축금리")
    private Double intrRate;

    @Column(name = "intr_rate2")
    @Comment("최고우대금리")
    private Double intrRate2;

    @Column(name = "rsrv_type")
    @Comment("적립유형")
    private String rsrvType;

    @Column(name = "rsrv_type_nm")
    @Comment("적립유형명")
    private String rsrvTypeNm;


    @Builder
    public ProductOption(long prdOptionSeq,String finCoNo, String finPrdtCd, String dclsMonth, Product product,
                         String intrRateType, String intrRateTypeNm, Integer saveTrm, Double intrRate,
                         Double intrRate2,
                         String rsrvType, String rsrvTypeNm) {
        this.prdOptionSeq = prdOptionSeq;
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
        this.dclsMonth = dclsMonth;
        this.product = product;
        this.intrRateType = intrRateType;
        this.intrRateTypeNm = intrRateTypeNm;
        this.saveTrm = saveTrm;
        this.intrRate = intrRate;
        this.intrRate2 = intrRate2;
        this.rsrvType = rsrvType;
        this.rsrvTypeNm = rsrvTypeNm;
    }
}