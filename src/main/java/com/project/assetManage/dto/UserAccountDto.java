package com.project.assetManage.dto;

import com.project.assetManage.entity.User;
import com.project.assetManage.entity.UserAccount;
import com.project.assetManage.entity.UserCard;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class UserAccountDto {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Request {

        private Integer accountSeq;
        private String accountNo;
        private Long userSeq;
        private String finCoNo;
        private Character useYn;

        public Request(Integer accountSeq
                , String accountNo
                , Long userSeq, String finCoNo, Character useYn) {
            this.accountSeq = accountSeq;
            this.accountNo = accountNo;
            this.userSeq = userSeq;
            this.finCoNo = finCoNo;
            this.useYn = useYn;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseAll {

        private String accountNo;
        private Long userSeq;
        private String finPrdtCd;
        private String finCoNo;
        private String dclsMonth;
        private Integer accountSeq;
        private LocalDateTime accountIssueDate;
        private LocalDateTime maturityDate;
        private LocalDateTime lastTranDate;
        private String productNm;
        private String productSubName;
        private Integer balanceAmt;
        private Character useYn;
        // @QueryProjection
        public ResponseAll(String accountNo, Long userSeq, String finPrdtCd, String finCoNo,
                        String dclsMonth, Integer accountSeq, LocalDateTime accountIssueDate,
                        LocalDateTime maturityDate, LocalDateTime lastTranDate, String productNm,
                        String productSubName, Integer balanceAmt, Character useYn) {
            this.accountNo = accountNo;
            this.userSeq = userSeq;
            this.finPrdtCd = finPrdtCd;
            this.finCoNo = finCoNo;
            this.dclsMonth = dclsMonth;
            this.accountSeq = accountSeq;
            this.accountIssueDate = accountIssueDate;
            this.maturityDate = maturityDate;
            this.lastTranDate = lastTranDate;
            this.productNm = productNm;
            this.productSubName = productSubName;
            this.balanceAmt = balanceAmt;
            this.useYn = useYn;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseCustom {

        private String accountNo;
        private Long userSeq;
        private String finPrdtCd;
        private String finCoNo;
        private String dclsMonth;
        private Integer accountSeq;
        private LocalDateTime accountIssueDate;
        private LocalDateTime maturityDate;
        private LocalDateTime lastTranDate;
        private String productNm;
        private String productSubName;
        private Integer balanceAmt;
        private Character useYn;

        // product
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
        private String actKindNm;
        private String joinWayNm;

        @QueryProjection
        public ResponseCustom(String accountNo, Long userSeq, String finPrdtCd, String finCoNo,
                        String dclsMonth, Integer accountSeq, LocalDateTime accountIssueDate,
                        LocalDateTime maturityDate, LocalDateTime lastTranDate, String productNm,
                        String productSubName, Integer balanceAmt, Character useYn
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
                , String joinWayNm) {
            this.accountNo = accountNo;
            this.userSeq = userSeq;
            this.finPrdtCd = finPrdtCd;
            this.finCoNo = finCoNo;
            this.dclsMonth = dclsMonth;
            this.accountSeq = accountSeq;
            this.accountIssueDate = accountIssueDate;
            this.maturityDate = maturityDate;
            this.lastTranDate = lastTranDate;
            this.productNm = productNm;
            this.productSubName = productSubName;
            this.balanceAmt = balanceAmt;
            this.useYn = useYn;

            // product
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
            this.actKindNm = actKindNm;
            this.joinWayNm = joinWayNm;
        }
    }

}
