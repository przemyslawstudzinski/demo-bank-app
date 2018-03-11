package org.banana.bank.dto;

import lombok.Getter;
import lombok.Setter;
import org.banana.bank.domain.TransactionType;

import java.math.BigDecimal;

public class TransactionDto {

    @Getter
    @Setter
    private TransactionType type;

    @Getter
    @Setter
    private BigDecimal value;
}
