package com.project.assetManage.entity.common;

import java.io.Serializable;
import java.util.Objects;

public class CodeId implements Serializable {
    private String groupCode;
    private String codeId;

    // 기본 생성자
    public CodeId() {
    }

    // 생성자
    public CodeId(String groupCode, String codeId) {
        this.groupCode = groupCode;
        this.codeId = codeId;
    }

    // equals 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeId codeId1 = (CodeId) o;
        return Objects.equals(groupCode, codeId1.groupCode) &&
                Objects.equals(codeId, codeId1.codeId);
    }

    // hashCode 메서드
    @Override
    public int hashCode() {
        return Objects.hash(groupCode, codeId);
    }
}