package com.project.assetManage.entity.common;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "st_cnsmp_incln")
public class ConsumptionInclination extends BaseDateTimeEntity {

    @Id
    @Column(name = "cnsmp_incln_cd")
    @Comment("소비성향코드")
    private String cnsmpInclnCd;

    @Column(name = "cnsmp_incln_title")
    @Comment("소비성향제목")
    private String cnsmpInclnTitle;

    @Column(name = "cnsmp_incln_contents")
    @Comment("소비성향설명")
    private String cnsmpInclnContents;

    @Column(name = "cnsmp_incln_img_url")
    @Comment("소비성향이미지url")
    private String cnsmpInclnImgUrl;

    @Column(name = "card_comput_rate")
    @Comment("카드지출산출비율(%)")
    private Integer cardComputRate;

    @Column(name = "cash_comput_rate")
    @Comment("현금지출산출비율(%)")
    private Integer cashComputRate;

    @Column(name = "deposit_comput_rate")
    @Comment("예금산출비율(%)")
    private Integer depositComputRate;

    @Column(name = "saving_comput_rate")
    @Comment("적금산출비율(%)")
    private Integer savingComputRate;

    @Column(name = "disp_seq")
    @Comment("표시순서")
    private Integer dispSeq;

    @Column(name = "use_yn")
    @Comment("사용여부")
    private Character useYn;


}
