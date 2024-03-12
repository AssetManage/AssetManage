package com.project.assetManage.dto;

import com.project.assetManage.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

public class ProductOptionDto {

    private ProductOptionDto productOptionDto;
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Request {
        // pk
        private Long prdOptionSeq;
        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;

        // etc
        private String cnsmpInclnCd;
        private String actKindCd;
        private String joinWayCd;
        private List<String> finCoNoList;
        private String rsrvType;

        private String ageCd;
        private String incomeScopeCd;
        private String prdtRcmdItemCd;

        // custom
        private String fetchOne;
        private int limit;

        private List<ResponseSimple> prdOptList;

        // optionList

        public Request(Long prdOptionSeq, String finCoNo, String finPrdtCd, String dclsMonth
        , String rsrvType, String cnsmpInclnCd
        , String actKindCd, String joinWayCd
        , String fetchOne
        , int limit
        , List<ResponseSimple> prdOptList
        , List<String> finCoNoList
        , String ageCd
        , String incomeScopeCd
        , String prdtRcmdItemCd) {
            this.prdOptionSeq = prdOptionSeq;
            this.finCoNo = finCoNo;
            this.finPrdtCd = finPrdtCd;
            this.dclsMonth = dclsMonth;

            this.rsrvType = rsrvType;
            this.cnsmpInclnCd = cnsmpInclnCd == null ? "AT" : cnsmpInclnCd;
            this.actKindCd = actKindCd;
            this.joinWayCd = joinWayCd;
            this.ageCd = ageCd;
            this.incomeScopeCd = incomeScopeCd;
            this.prdtRcmdItemCd = prdtRcmdItemCd;

            // custom
            this.fetchOne = fetchOne;
            this.limit = limit;
            this.prdOptList = prdOptList;
            this.finCoNoList = finCoNoList;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseSimple {

        private Long prdOptionSeq;
        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;

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
        private String cnsmpInclnCdListStr;

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
                , int maturityAmt
                , String cnsmpInclnCdListStr) {

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
            this.cnsmpInclnCdListStr = cnsmpInclnCdListStr;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseCustom {

        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;
        // custom
        private Long maxPrdOptionSeq;
        private Long minPrdOptionSeq;
        private Long avgPrdOptionSeq;
        private String cnsmpInclnCdListStr;

        @QueryProjection
        public ResponseCustom(String finCoNo, String finPrdtCd, String dclsMonth
                , Long maxPrdOptionSeq
                , Long minPrdOptionSeq
                , Long avgPrdOptionSeq
                , String cnsmpInclnCdListStr) {
            this.finCoNo = finCoNo;
            this.finPrdtCd = finPrdtCd;
            this.dclsMonth = dclsMonth;
            this.maxPrdOptionSeq = maxPrdOptionSeq;
            this.minPrdOptionSeq = minPrdOptionSeq;
            this.avgPrdOptionSeq = avgPrdOptionSeq;
            this.cnsmpInclnCdListStr = cnsmpInclnCdListStr;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class Result {
        private String stat;
        private int size;
        private List<?> list;

        public Result(String stat, List<?> list) {
            this.stat = stat;
            this.size = list.size();
            this.list = list;
        }
    }
}
