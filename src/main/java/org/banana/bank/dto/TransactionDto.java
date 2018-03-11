package org.banana.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class TransactionDto {

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private BigDecimal value;
}
