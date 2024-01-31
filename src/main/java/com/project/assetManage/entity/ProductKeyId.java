package com.project.assetManage.entity;

import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductKeyId implements Serializable {

    private String finCoNo;
    private String finPrdtCd;
    private String dclsMonth;
    public ProductKeyId() {}

    public ProductKeyId(String finCoNo, String finPrdtCd, String dclsMonth) {
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
        this.dclsMonth = dclsMonth;
    }

    // equals 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductKeyId that = (ProductKeyId) o;
        return finCoNo.equals(that.finCoNo) && finPrdtCd.equals(that.finPrdtCd) && dclsMonth.equals(that.dclsMonth);
    }

    // hashCode 메서드 구현
    @Override
    public int hashCode() {
        return Objects.hash(finCoNo, finPrdtCd, dclsMonth);
    }

}
