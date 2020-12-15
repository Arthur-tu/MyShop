package shop.example.shop.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import shop.example.shop.domain.User;
import shop.example.shop.dto.UserDto;
import java.util.List;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto dto);
    @InheritInverseConfiguration
    UserDto fromUser(User user);

    List<User> toUserList(List<UserDto> userDtos);

    List<UserDto> fromUserList(List<User> users);
}
