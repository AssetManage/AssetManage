package com.project.assetManage.entity;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class TransactionHistoryId implements Serializable {

    private String cardId;
    private String approvalNum;

    public TransactionHistoryId() {
    }

    public TransactionHistoryId(String cardId, String approvalNum) {
        this.cardId = cardId;
        this.approvalNum = approvalNum;
    }

    // equals 및 hashCode 메서드 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionHistoryId that = (TransactionHistoryId) o;
        return Objects.equals(cardId, that.cardId) &&
                Objects.equals(approvalNum, that.approvalNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, approvalNum);
    }
}
