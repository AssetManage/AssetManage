package com.project.assetManage.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@NoArgsConstructor
public class ProductOptionDto {

    private Long prdOptionSeq;
    private String finCoNo;
    private String finPrdtCd;
    private String dclsMonth;
    private String intrRateType;
    private String intrRateTypeNm;
    private Integer saveTrm;
    private Double intrRate;
    private Double intrRate2;
    private String rsrvType;
    private String rsrvTypeNm;

    // custom
    private Long maturityAmt;

    @QueryProjection
    public ProductOptionDto(Long prdOptionSeq
            , String finCoNo
            , String finPrdtCd
            , String dclsMonth
            , String intrRateType
            , String intrRateTypeNm
            , Integer saveTrm
            , Double intrRate
            , Double intrRate2
            , String rsrvType
            , String rsrvTypeNm
            , Long maturityAmt) {

        this.prdOptionSeq = prdOptionSeq;
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
        this.dclsMonth = dclsMonth;
        this.intrRateType = intrRateType;
        this.intrRateTypeNm = intrRateTypeNm;
        this.saveTrm = saveTrm;
        this.intrRate = intrRate;
        this.intrRate2 = intrRate2;
        this.rsrvType = rsrvType;
        this.rsrvTypeNm = rsrvTypeNm;

        // custom
        this.maturityAmt = maturityAmt;
    }

}
