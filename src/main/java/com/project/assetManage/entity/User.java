package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "st_user")
public class User extends BaseDateTimeEntity {

    @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_seq")
    @Comment("회원번호")
    private String userSeq;

    @Column(name="user_nm")
    @Comment("성명")
    private String userNm;

    @Column(name="login_id")
    @Comment("로그인ID")
    private String loginId;

    @Column(name="login_pw")
    @Comment("로그인PW")
    private String loginPw;

    @Column(name="email")
    @Comment("이메일")
    private String email;

    @Column(name="prdt_rcmd_item_cd")
    @Comment("상품추천항목코드")
    private String prdtRcmdItemCd;

    @Column(name="lock_yn")
    @Comment("잠금여부")
    private Character lockYn;

    @Column(name="secsn_yn")
    @Comment("탈퇴여부")
    private Character secsnYn;

    @Column(name="indvdlinfo_agree_yn")
    @Comment("개인정보동의여부")
    private Character indvdlinfoAgreeYn;

    @Column(name="profile_img_url")
    @Comment("프로필이미지url")
    private String profileImgUrl;

    @Column(name="sex_cd")
    @Comment("성별")
    private String sexCd;

    @Column(name="age_cd")
    @Comment("연령대")
    private String ageCd;

    @Column(name="age")
    @Comment("나이")
    private Integer age;

    @Column(name="occupation_cd")
    @Comment("직업군")
    private String occupationCd;

    @Column(name="mobile_tel_num")
    @Comment("핸드폰번호")
    private String mobileTelNum;

    @Column(name="zip_cd")
    @Comment("우편번호")
    private String zipCd;

    @Column(name="zip_detail_addr1")
    @Comment("상세주소1")
    private String zipDetailAddr1;

    @Column(name = "zip_detail_addr2")
    @Comment("상세주소2")
    private String zipDetailAddr2;


    @Builder
    public User(String userSeq, String userNm, String loginId, String loginPw, String email,
                  String prdtRcmdItemCd, Character lockYn, Character secsnYn, Character indvdlinfoAgreeYn,
                  String profileImgUrl, String sexCd, String ageCd, Integer age, String occupationCd,
                  String mobileTelNum, String zipCd, String zipDetailAddr1, String zipDetailAddr2) {
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
    }


/*    public void updateConsumtionCode(Long code){
        this.consumptionCode = code;
    }*/
}
