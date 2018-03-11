package org.banana.bank.domain;

import lombok.Getter;

public enum TransactionType {
    INCREASE("Increase"),
    DECREASE("Decrease");

    @Getter
    private String type;

    TransactionType(String type) {
        this.type = type;
    }
}
