package com.project.assetManage.dto;

import com.project.assetManage.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

public class ProductOptionDto {

    private ProductOptionDto productOptionDto;

    @NoArgsConstructor
    @Getter
    public static class ResponseSimple {

        private Long prdOptionSeq;
        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;

        @QueryProjection
        public ResponseSimple(Long prdOptionSeq, String finCoNo, String finPrdtCd, String dclsMonth) {
            this.prdOptionSeq = prdOptionSeq;
            this.finCoNo = finCoNo;
            this.finPrdtCd = finPrdtCd;
            this.dclsMonth = dclsMonth;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseAll {

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
        private int maturityAmt;

        @QueryProjection
        public ResponseAll(Long prdOptionSeq
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
                , int maturityAmt) {

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
}
