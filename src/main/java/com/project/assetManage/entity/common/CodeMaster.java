package com.project.assetManage.entity.common;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name ="code_master")
public class CodeMaster extends BaseDateTimeEntity {

    @Id
    @Column(name = "group_code")
    private String groupCode;

    @Id
    @Column(name = "code_id")
    private String codeId;
    @Column(name = "code_name")
    private String codeName;
    @Column(name = "etc1")
    private String etc1;
    @Column(name = "etc2")
    private String etc2;
    @Column(name = "etc3")
    private String etc3;
}
