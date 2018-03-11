package spring.mvc.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.mvc.example.domain.Transaction;
import spring.mvc.example.dto.TransactionDto;

import java.util.List;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto toTransactionDto(Transaction transaction);

    List<TransactionDto> toTransactionDtos(List<Transaction> districts);
}
