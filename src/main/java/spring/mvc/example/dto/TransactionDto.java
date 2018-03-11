package spring.mvc.example.dto;

import lombok.Getter;
import lombok.Setter;
import spring.mvc.example.domain.enums.OperationType;

import java.math.BigDecimal;

public class TransactionDto {

    @Getter
    @Setter
    private OperationType type;

    @Getter
    @Setter
    private BigDecimal value;
}
