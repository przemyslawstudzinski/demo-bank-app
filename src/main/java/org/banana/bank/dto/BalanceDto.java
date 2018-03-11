package org.banana.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class BalanceDto {

    @Getter
    @Setter
    private BigDecimal value;
}
