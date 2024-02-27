package com.project.assetManage.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ProductDto {
    @NoArgsConstructor
    @Getter
    public static class ResponseSimple {

        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;

        // @QueryProjection
        public ResponseSimple(String finCoNo, String finPrdtCd, String dclsMonth) {
            this.finCoNo = finCoNo;
            this.finPrdtCd = finPrdtCd;
            this.dclsMonth = dclsMonth;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseAll {

        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;
        private String actKindCd;
        private String korCoNm;
        private String finPrdtNm;
        private String joinWay;
        private String mtrtInt;
        private String spclCnd;
        private String joinDeny;
        private String joinMember;
        private String etcNote;
        private Integer maxLmit;
        private String dclsStrtDay;
        private String dclsEndDay;
        private String finCoSubmDay;
        private String joinWayCd;

        // custom
        private String actKindNm;
        private String joinWayNm;
        private String joinWayElCnts;
        private String cnsmpInclnCdList;

        // option
        @Setter
        private List<ProductOptionDto.ResponseAll> productOptionList;

        // @QueryProjection
        public ResponseAll(String finCoNo, String finPrdtCd, String dclsMonth
                , String actKindCd
                , String korCoNm
                , String finPrdtNm
                , String joinWay
                , String mtrtInt
                , String spclCnd
                , String joinDeny
                , String joinMember
                , String etcNote
                , Integer maxLmit
                , String dclsStrtDay
                , String dclsEndDay
                , String finCoSubmDay
                , String joinWayCd
                , String actKindNm
                , String joinWayNm
                , String joinWayElCnts
                , String cnsmpInclnCdList
                , List<ProductOptionDto.ResponseAll> productOptionList) {
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

            //custom
            this.actKindNm = actKindNm;
            this.joinWayNm = joinWayNm;
            this.joinWayElCnts = joinWayElCnts;
            this.cnsmpInclnCdList = cnsmpInclnCdList;

            // option
            this.productOptionList = productOptionList;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseCustom {
        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;
        private String actKindCd;
        private String korCoNm;
        private String finPrdtNm;
        private String joinWay;
        private String mtrtInt;
        private String spclCnd;
        private String joinDeny;
        private String joinMember;
        private String etcNote;
        private Integer maxLmit;
        private String dclsStrtDay;
        private String dclsEndDay;
        private String finCoSubmDay;
        private String joinWayCd;
        // custom
        private String actKindNm;
        private String joinWayNm;
        private String joinWayElCnts;
        private String cnsmpInclnCdList;

        // option
        private Long prdOptionSeq;
        private String intrRateType;
        private String intrRateTypeNm;
        private Integer saveTrm;
        private Double intrRate;
        private Double intrRate2;
        private String rsrvType;
        private String rsrvTypeNm;
        // custom
        private int maturityAmt;
        private int rnum;

        // @QueryProjection
        public ResponseCustom(String finCoNo, String finPrdtCd, String dclsMonth
                , String actKindCd
                , String korCoNm
                , String finPrdtNm
                , String joinWay
                , String mtrtInt
                , String spclCnd
                , String joinDeny
                , String joinMember
                , String etcNote
                , Integer maxLmit
                , String dclsStrtDay
                , String dclsEndDay
                , String finCoSubmDay
                , String joinWayCd
                , String actKindNm
                , String joinWayNm
                , String joinWayElCnts
                , String cnsmpInclnCdList
                , Long prdOptionSeq
                , String intrRateType
                , String intrRateTypeNm
                , Integer saveTrm
                , Double intrRate
                , Double intrRate2
                , String rsrvType
                , String rsrvTypeNm
                , int maturityAmt
                , int rnum) {
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

            //custom
            this.actKindNm = actKindNm;
            this.joinWayNm = joinWayNm;
            this.joinWayElCnts = joinWayElCnts;
            this.cnsmpInclnCdList = cnsmpInclnCdList;

            // option
            this.prdOptionSeq = prdOptionSeq;
            this.intrRateType = intrRateType;
            this.intrRateTypeNm = intrRateTypeNm;
            this.saveTrm = saveTrm;
            this.intrRate = intrRate;
            this.intrRate2 = intrRate2;
            this.rsrvType = rsrvType;
            this.rsrvTypeNm = rsrvTypeNm;

            // custom
            this.maturityAmt = maturityAmt;
            this.rnum = rnum;
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
