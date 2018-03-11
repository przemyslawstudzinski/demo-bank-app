package org.banana.bank.dto;

import lombok.Getter;
import lombok.Setter;
import org.banana.bank.constant.ValidationMessages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class DecreaseDto {

    @Getter
    @Setter
    @Positive
    @NotNull(message = ValidationMessages.EMPTY)
    private BigDecimal value;

    @Getter
    @Setter
    @NotEmpty(message = ValidationMessages.EMPTY)
    private String token;
}
