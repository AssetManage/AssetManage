package com.project.assetManage.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductKeyId implements Serializable {

    private String finCoNo;
    private String finPrdtCd;
    public ProductKeyId() {}

    // 매개변수를 받는 생성자
    public ProductKeyId(String finCoNo, String finPrdtCd) {
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
    }

    // equals 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductKeyId that = (ProductKeyId) o;
        return finCoNo.equals(that.finCoNo) && finPrdtCd.equals(that.finPrdtCd);
    }

    // hashCode 메서드 구현
    @Override
    public int hashCode() {
        return Objects.hash(finCoNo, finPrdtCd);
    }

}
