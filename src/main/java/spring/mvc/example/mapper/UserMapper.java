package spring.mvc.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import spring.mvc.example.domain.User;
import spring.mvc.example.dto.BalanceDto;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({@Mapping(target = "value", source = "balance")})
    BalanceDto toBalanceDto(User user);
}
