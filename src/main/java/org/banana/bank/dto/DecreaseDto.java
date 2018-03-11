package org.banana.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class DecreaseDto {

    @Getter
    @Setter
    private BigDecimal value;

    @Getter
    @Setter
    private String token;
}
