package com.project.assetManage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class CodeDto {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Request {

        private String groupCode;
        private String codeId;
        private String codeNm;
        private String etc1;
        private String etc2;
        private String etc3;
        private Character useYn;

        // custom
        private List<?> codeIdList;
        private String codeIdListStr;

        public Request(String groupCode, String codeId, String codeNm
                , String etc1
                , String etc2
                , String etc3
                , Character useYn
                , List<?> codeIdList
                , String codeIdListStr) {
            this.groupCode = groupCode;
            this.codeId = codeId;
            this.codeNm = codeNm;
            this.etc1 = etc1;
            this.etc2 = etc2;
            this.etc3 = etc3;
            this.useYn = useYn;
            // custom
            this.codeIdList = codeIdList;
            this.codeIdListStr = codeIdListStr;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseSimple {

        private String groupCode;
        private String codeId;
        private String codeNm;

        public ResponseSimple(String groupCode, String codeId, String codeNm) {
            this.groupCode = groupCode;
            this.codeId = codeId;
            this.codeNm = codeNm;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseAll {

        private String groupCode;
        private String codeId;
        private String codeNm;
        private Integer dispSeq;
        private String rmk;
        private String etc1;
        private String etc2;
        private String etc3;
        private Character useYn;

        public ResponseAll(String groupCode, String codeId, String codeNm
                , Integer dispSeq
                , String rmk
                , String etc1
                , String etc2
                , String etc3
                , Character useYn) {
            this.groupCode = groupCode;
            this.codeId = codeId;
            this.codeNm = codeNm;
            this.dispSeq = dispSeq;
            this.rmk = rmk;
            this.etc1 = etc1;
            this.etc2 = etc2;
            this.etc3 = etc3;
            this.useYn = useYn;
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
