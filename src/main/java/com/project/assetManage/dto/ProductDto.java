package com.project.assetManage.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductDto {
    @Getter
    // @AllArgsConstructor
    public static class ResponseSimple {
        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;

        @QueryProjection
        public ResponseSimple(String finCoNo, String finPrdtCd, String dclsMonth) {
            this.finCoNo = finCoNo;
            this.finPrdtCd = finPrdtCd;
            this.dclsMonth = dclsMonth;
        }
    }

    @Getter
    // @AllArgsConstructor
    public static class ResponseAll {
        private String finCoNo;
        private String finPrdtCd;
        private String dclsMonth;

        @QueryProjection
        public ResponseAll(String finCoNo, String finPrdtCd, String dclsMonthmaturityAmt) {
            this.finCoNo = finCoNo;
            this.finPrdtCd = finPrdtCd;
            this.dclsMonth = dclsMonth;
        }
    }

}
