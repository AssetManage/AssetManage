package com.project.assetManage.entity;


import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "api_products_option")
@IdClass(ProductOptionKeyId.class)
public class ProductOption extends BaseDateTimeEntity {

    @Id
    @Column(name = "prd_option_seq")
    private Long prdOptionSeq;

    @Id
    @Column(name = "fin_co_no")
    private String finCoNo;

    @Id
    @Column(name = "fin_prdt_cd")
    private String finPrdtCd;

    @Id
    @Column(name = "dcls_month")
    private String dclsMonth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "fin_co_no", insertable = false, updatable = false),
        @JoinColumn(name = "fin_prdt_cd", insertable = false, updatable = false),
        @JoinColumn(name = "dcls_month", insertable = false, updatable = false)
    })
    private Product product;

    @Column(name = "intr_rate_type")
    private String intrRateType;

    @Column(name = "intr_rate_type_nm")
    private String intrRateTypeNm;

    @Column(name = "save_trm")
    private Integer saveTrm;

    @Column(name = "intr_rate")
    private Double intrRate;

    @Column(name = "intr_rate2")
    private Double intrRate2;

    @Column(name = "rsrv_type")
    private String rsrvType;

    @Column(name = "rsrv_type_nm")
    private String rsrvTypeNm;


    @Builder
    public ProductOption(String finCoNo, String finPrdtCd, String dclsMonth, Product product,
        String intrRateType, String intrRateTypeNm, Integer saveTrm, Double intrRate,
        Double intrRate2,
        String rsrvType, String rsrvTypeNm) {
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
