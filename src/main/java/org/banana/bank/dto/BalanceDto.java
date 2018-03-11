package org.banana.bank.dto;

import lombok.Getter;
import lombok.Setter;
import org.banana.bank.constant.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BalanceDto {

    @Getter
    @Setter
    @NotNull(message = ValidationMessages.EMPTY)
    private BigDecimal value;
}
