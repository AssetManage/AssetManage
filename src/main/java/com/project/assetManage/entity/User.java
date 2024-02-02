package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "st_user")
public class User extends BaseDateTimeEntity {

    @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_seq")
    private String userSeq;

    @Column(name="user_nm")
    private String userNm;

    @Column(name="login_id")
    private String loginId;

    @Column(name="login_pw")
    private String loginPw;

    @Column(name="email")
    private String email;

    @Column(name="prdt_rcmd_item_cd")
    private String prdtRcmdItemCd;

    @Column(name="lock_yn")
    private Character lockYn;

    @Column(name="secsn_yn")
    private Character secsnYn;

    @Column(name="indvdlinfo_agree_yn")
    private Character indvdlinfoAgreeYn;

    @Column(name="profile_img_url")
    private String profileImgUrl;

    @Column(name="sex_cd", columnDefinition = "VARCHAR(40)")
    private Gender sexCd;

    @Column(name="age_cd")
    private String ageCd;

    @Column(name="age")
    private Integer age;

    @Column(name="occupation_cd")
    private String occupationCd;

    @Column(name="mobile_tel_num")
    private String mobileTelNum;

    @Column(name="zip_cd")
    private String zipCd;

    @Column(name="zip_detail_addr1")
    private String zipDetailAddr1;

    @Column(name = "zip_detail_addr2")
    private String zipDetailAddr2;


    @Builder
    public User(String userSeq, String userNm, String loginId, String loginPw, String email,
                  String prdtRcmdItemCd, Character lockYn, Character secsnYn, Character indvdlinfoAgreeYn,
                  String profileImgUrl, Gender sexCd, String ageCd, Integer age, String occupationCd,
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
