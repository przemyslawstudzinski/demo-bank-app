package spring.mvc.example.domain.enums;

import lombok.Getter;

public enum OperationType {
    INCREASE("Increase"),
    DECREASE("Decrease");

    @Getter
    private String type;

    OperationType(String type) {
        this.type = type;
    }
}
