package com.project.assetManage.dto;

import com.project.assetManage.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
        message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    private String userNm;
    private String loginId;
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
    private String prdt_rcmd_item_cd;


    @Builder
    public SignRequest(String email, String password, String userNm,
                       Character indvdlinfoAgreeYn, String profileImgUrl, String sexCd, String ageCd, Integer age,
                       String occupationCd, String mobileTelNum, String zipCd, String zipDetailAddr1,
                       String zipDetailAddr2, String prdtRcmdItemCd) {
        this.email = email;
        this.password = password;
        this.userNm = userNm;
        this.loginId = email;
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
        prdt_rcmd_item_cd = prdtRcmdItemCd;
    }

    public User toUserEntity(String password, SignRequest request){
        return User.builder()
            .email(request.getEmail())
            .loginId(request.getEmail())
            .loginPw(password)
            .userNm(request.getUserNm())
            .age(request.getAge())
            .ageCd(request.getAgeCd())
            .secsnYn('N')
            .lockYn('N')
            .indvdlinfoAgreeYn(request.getIndvdlinfoAgreeYn())
            .mobileTelNum(request.getMobileTelNum())
            .occupationCd(request.getOccupationCd())
            .sexCd(request.getSexCd())
            .profileImgUrl(request.getProfileImgUrl())
            .zipCd(request.getZipCd())
            .zipDetailAddr1(request.getZipDetailAddr1())
            .zipDetailAddr2(request.getZipDetailAddr2())
            .prdtRcmdItemCd(request.getPrdt_rcmd_item_cd())
            .build();
    }

}