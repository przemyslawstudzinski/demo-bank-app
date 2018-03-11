package org.banana.bank.mapper;

import org.banana.bank.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.banana.bank.dto.TransactionDto;

import java.util.List;

@Mapper(uses = TransactionTypeMapper.class)
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    List<TransactionDto> toTransactionDtos(List<Transaction> transactions);
}
