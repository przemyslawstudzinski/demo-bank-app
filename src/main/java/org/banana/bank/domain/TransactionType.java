package org.banana.bank.domain;

import lombok.Getter;

import java.util.stream.Stream;

public enum TransactionType {
    INCREASE("Increase"),
    DECREASE("Decrease");

    @Getter
    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    /**
     * Return an enum value from display name.
     *
     * @param name display name of enum value
     */
    public static TransactionType getByDisplayName(String name) {
//        Stream.of(TransactionType.values()).filter(
//                x -> x.getType().equalsIgnoreCase(name)).findFirst().orElse(null);

        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.getType().equalsIgnoreCase(name)) {
                return transactionType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return type.toLowerCase();
    }
}
