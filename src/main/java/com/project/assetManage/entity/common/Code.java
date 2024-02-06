package com.project.assetManage.entity.common;

import com.project.assetManage.entity.UserCard;
import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name ="st_code")
public class Code extends BaseDateTimeEntity {

    @Id
    @Column(name = "grp_code_id")
    @Comment("그룹코드ID")
    private String groupCode;

    @Id
    @Column(name = "code_id")
    @Comment("코드ID")
    private String codeId;

    @Column(name = "code_nm")
    @Comment("코드명")
    private String codeNm;

    @Column(name = "disp_seq")
    @Comment("표시순서")
    private Integer dispSeq;

    @Column(name = "rmk")
    @Comment("비고")
    private String rmk;

    @Column(name = "etc1")
    @Comment("예약1")
    private String etc1;

    @Column(name = "etc2")
    @Comment("예약2")
    private String etc2;

    @Column(name = "etc3")
    @Comment("예약3")
    private String etc3;

    @Column(name = "use_yn")
    @Comment("사용여부")
    private Character useYn;




}
