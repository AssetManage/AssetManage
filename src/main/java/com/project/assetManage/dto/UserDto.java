package com.project.assetManage.dto;

import com.project.assetManage.entity.User;
import com.project.assetManage.entity.UserCard;
import lombok.*;

import java.util.List;

public class UserDto {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Request {

        private Long userSeq;
        private String userNm;
        private String loginId;
        private String loginPw;
        private String ageCd;

        private String profileImgUrl;
        private String sexCd;
        private int age;
        private String occupationCd;//직업

        private String mobileTelNum;
        private String zipCd;
        private String zipDetailAddr1;
        private String zipDetailAddr2;

        private String prdtRcmdItemCd; //상품추천항목

        public Request(Long userSeq, String userNm, String loginId, String loginPw, String ageCd, String profileImgUrl, String sexCd, int age, String occupationCd, String mobileTelNum, String zipCd, String zipDetailAddr1, String zipDetailAddr2, String prdtRcmdItemCd) {
            this.userSeq = userSeq;
            this.userNm = userNm;
            this.loginId = loginId;
            this.loginPw = loginPw;
            this.ageCd = ageCd;
            this.profileImgUrl = profileImgUrl;
            this.sexCd = sexCd;
            this.age = age;
            this.occupationCd = occupationCd;
            this.mobileTelNum = mobileTelNum;
            this.zipCd = zipCd;
            this.zipDetailAddr1 = zipDetailAddr1;
            this.zipDetailAddr2 = zipDetailAddr2;
            this.prdtRcmdItemCd = prdtRcmdItemCd;
        }

        public Request(Long userSeq){
            this.userSeq = userSeq;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseSimple {

        private Long userSeq;
        private String userNm;
        private String prdtRcmdItemCd;
        private String cnsmpInclnCd;
        private String ageCd;
        private String incomeScopeCd;
        private String prdtRcmdItemNm;
        private String cnsmpInclnNm;
        private String ageNm;
        private String incomeScopeNm;


        public ResponseSimple(Long userSeq, String userNm
                , String ageCd
                , String prdtRcmdItemCd
                , String cnsmpInclnCd
                , String incomeScopeCd
                , String ageNm
                , String prdtRcmdItemNm
                , String cnsmpInclnNm
                , String incomeScopeNm
        ) {
            this.userSeq = userSeq;
            this.userNm = userNm;
            this.ageCd = ageCd;
            this.prdtRcmdItemCd = prdtRcmdItemCd;
            this.cnsmpInclnCd = cnsmpInclnCd;
            this.incomeScopeCd = incomeScopeCd;
            this.ageNm = ageNm;
            this.prdtRcmdItemNm = prdtRcmdItemNm;
            this.cnsmpInclnNm = cnsmpInclnNm;
            this.incomeScopeNm = incomeScopeNm;

        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseAll {

        private Long userSeq;
        private String userNm;
        private String loginId;
        private String loginPw;
        private String email;
        private String prdtRcmdItemCd;
        private Character lockYn;
        private Character secsnYn;
        private Character indvdlinfoAgreeYn;
        private String profileImgUrl;
        private String sexCd;
        private String ageCd;
        private Integer age;
        private String occupationCd;
        private String mobileTelNum;
        private String zipCd;
        private String zipDetailAddr1;
        private String zipDetailAddr2;

        // UserComuteIncome
        private String cnsmpInclnCd;
        private String incomeScopeCd;
        private String cnsmpInclnNm;
        private String incomeScopeNm;

        // UserAccount
        @Setter
        private List<?> userAccountList;
        // UserCard
        @Setter
        private List<UserCard> userCardList;

        public ResponseAll(Long userSeq, String userNm, String loginId, String loginPw, String email,
                              String prdtRcmdItemCd, Character lockYn, Character secsnYn, Character indvdlinfoAgreeYn,
                              String profileImgUrl, String sexCd, String ageCd, Integer age, String occupationCd,
                              String mobileTelNum, String zipCd, String zipDetailAddr1, String zipDetailAddr2
                , String cnsmpInclnCd, String incomeScopeCd
                , String cnsmpInclnNm, String incomeScopeNm
                , List<?> userAccountList
                , List<UserCard> userCardList
            ) {

            this.userSeq = userSeq;
            this.userNm = userNm;
            this.loginId = loginId;
            this.loginPw = loginPw;
            this.email = email;
            this.prdtRcmdItemCd = prdtRcmdItemCd;
            this.lockYn = lockYn;
            this.secsnYn = secsnYn;
            this.indvdlinfoAgreeYn = indvdlinfoAgreeYn;
            this.profileImgUrl = profileImgUrl;
            this.sexCd = sexCd;
            this.ageCd = ageCd;
            this.age = age;
            this.occupationCd = occupationCd;
            this.mobileTelNum = mobileTelNum;
            this.zipCd = zipCd;
            this.zipDetailAddr1 = zipDetailAddr1;
            this.zipDetailAddr2 = zipDetailAddr2;

            // UserComuteIncome
            this.cnsmpInclnCd = cnsmpInclnCd;
            this.incomeScopeCd = incomeScopeCd;
            this.cnsmpInclnNm = cnsmpInclnNm;
            this.incomeScopeNm = incomeScopeNm;

            this.userAccountList = userAccountList;
            this.userCardList = userCardList;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseCustom {

        private Long userSeq;
        private String userNm;
        private String loginId;
        private String loginPw;
        private String email;
        private String prdtRcmdItemCd;
        private Character lockYn;
        private Character secsnYn;
        private Character indvdlinfoAgreeYn;
        private String profileImgUrl;
        private String sexCd;
        private String ageCd;
        private Integer age;
        private String occupationCd;
        private String mobileTelNum;
        private String zipCd;
        private String zipDetailAddr1;
        private String zipDetailAddr2;
        private String prdtRcmdItemNm;
        private String occupationNm;

        // UserComuteIncome
        private String cnsmpInclnCd;
        private String incomeScopeCd;
        private String cnsmpInclnNm;
        private String incomeScopeNm;
        private Integer yearIncome;
        private Integer cardExpdtAmt;
        private Integer cashExpdtAmt;
        private Integer savingExpdtAmt;

        // UserAccount
        @Setter
        private List<?> userAccountList;
        // UserCard
        @Setter
        private List<UserCard> userCardList;

        public ResponseCustom(Long userSeq, String userNm, String loginId, String loginPw, String email,
                           String prdtRcmdItemCd, Character lockYn, Character secsnYn, Character indvdlinfoAgreeYn,
                           String profileImgUrl, String sexCd, String ageCd, Integer age, String occupationCd,
                           String mobileTelNum, String zipCd, String zipDetailAddr1, String zipDetailAddr2
                , String prdtRcmdItemNm
                , String occupationNm
                , String cnsmpInclnCd
                , String incomeScopeCd
                , Integer yearIncome
                , Integer cardExpdtAmt
                , Integer cashExpdtAmt
                , Integer savingExpdtAmt
                , String cnsmpInclnNm
                , String incomeScopeNm
                , List<?> userAccountList
                , List<UserCard> userCardList) {

            this.userSeq = userSeq;
            this.userNm = userNm;
            this.loginId = loginId;
            this.loginPw = loginPw;
            this.email = email;
            this.prdtRcmdItemCd = prdtRcmdItemCd;
            this.lockYn = lockYn;
            this.secsnYn = secsnYn;
            this.indvdlinfoAgreeYn = indvdlinfoAgreeYn;
            this.profileImgUrl = profileImgUrl;
            this.sexCd = sexCd;
            this.ageCd = ageCd;
            this.age = age;
            this.occupationCd = occupationCd;
            this.mobileTelNum = mobileTelNum;
            this.zipCd = zipCd;
            this.zipDetailAddr1 = zipDetailAddr1;
            this.zipDetailAddr2 = zipDetailAddr2;
            this.occupationNm = occupationNm;
            this.prdtRcmdItemNm = prdtRcmdItemNm;

            // UserComuteIncome
            this.cnsmpInclnCd = cnsmpInclnCd;
            this.incomeScopeCd = incomeScopeCd;
            this.yearIncome = yearIncome;
            this.cardExpdtAmt = cardExpdtAmt;
            this.cashExpdtAmt = cashExpdtAmt;
            this.savingExpdtAmt = savingExpdtAmt;
            this.cnsmpInclnNm = cnsmpInclnNm;
            this.incomeScopeNm = incomeScopeNm;

            // UserAccount
            this.userAccountList = userAccountList;
            // UserCard
            this.userCardList = userCardList;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResultOne {
        private String stat;
        private Object data;

        public ResultOne(String stat, Object data) {
            this.stat = stat;
            this.data = data;
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
            this.list = list;
            this.size = list.size();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserMyInfoResponseDTO {
        private Long userSeq;
        private String userNm;
        private String email;
        private String profileImgUrl;
        private String sexCd;
        private int age;
        private String occupationCd;//직업

        private String mobileTelNum;
        private String zipCd;
        private String zipDetailAddr1;
        private String zipDetailAddr2;

        private String prdtRcmdItemCd; //상품추천항목

        private String cnsmpInclnCd; //소비성향
        private String cnsmpInclnNm;

        @Builder
        public UserMyInfoResponseDTO(Long userSeq, String userNm, String email, String profileImgUrl, String sexCd, int age, String occupationCd, String mobileTelNum, String zipCd, String zipDetailAddr1, String zipDetailAddr2, String prdtRcmdItemCd, String cnsmpInclnCd, String cnsmpInclnNm) {
            this.userSeq = userSeq;
            this.userNm = userNm;
            this.email = email;
            this.profileImgUrl = profileImgUrl;
            this.sexCd = sexCd;
            this.age = age;
            this.occupationCd = occupationCd;
            this.mobileTelNum = mobileTelNum;
            this.zipCd = zipCd;
            this.zipDetailAddr1 = zipDetailAddr1;
            this.zipDetailAddr2 = zipDetailAddr2;
            this.prdtRcmdItemCd = prdtRcmdItemCd;
            this.cnsmpInclnCd = cnsmpInclnCd;
            this.cnsmpInclnNm = cnsmpInclnNm;
        }
    }

    @Getter
    @Setter
    public static class UpdateDTO{
        private Long userSeq;
        private String userNm;
        private String profileImgUrl;
        private String sexCd;
        private int age;
        private String occupationCd;//직업

        private String mobileTelNum;
        private String zipCd;
        private String zipDetailAddr1;
        private String zipDetailAddr2;

        private String prdtRcmdItemCd; //상품추천항목

        public UpdateDTO(User userUpdate) {
            this.userSeq = userUpdate.getUserSeq();
            this.userNm = userUpdate.getUserNm();
            this.profileImgUrl = userUpdate.getProfileImgUrl();
            this.sexCd = userUpdate.getSexCd();
            this.age = userUpdate.getAge();
            this.occupationCd = userUpdate.getOccupationCd();
            this.mobileTelNum = userUpdate.getMobileTelNum();
            this.zipCd = userUpdate.getZipCd();
            this.zipDetailAddr1 = userUpdate.getZipDetailAddr1();
            this.zipDetailAddr2 = userUpdate.getZipDetailAddr2();
            this.prdtRcmdItemCd = userUpdate.getPrdtRcmdItemCd();
        }
    }


}
