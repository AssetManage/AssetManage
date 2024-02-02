package com.project.assetManage.entity;

import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductOptionKeyId implements Serializable {

    @EqualsAndHashCode.Include
    private Long prdOptionSeq;
    @EqualsAndHashCode.Include
    private String finCoNo;
    @EqualsAndHashCode.Include
    private String finPrdtCd;
    @EqualsAndHashCode.Include
    private String dclsMonth;
    public ProductOptionKeyId() {}

    public ProductOptionKeyId(Long prdOptionSeq, String finCoNo, String finPrdtCd, String dclsMonth) {
        this.prdOptionSeq = prdOptionSeq;
        this.finCoNo = finCoNo;
        this.finPrdtCd = finPrdtCd;
        this.dclsMonth = dclsMonth;
    }

}
