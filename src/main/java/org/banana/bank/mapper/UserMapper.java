package org.banana.bank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import org.banana.bank.domain.User;
import org.banana.bank.dto.BalanceDto;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({@Mapping(target = "value", source = "balance")})
    BalanceDto toBalanceDto(User user);
}
