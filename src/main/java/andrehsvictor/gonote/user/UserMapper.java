package andrehsvictor.gonote.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import andrehsvictor.gonote.user.dto.CreateUserDto;
import andrehsvictor.gonote.user.dto.UpdateUserDto;
import andrehsvictor.gonote.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User createUserDtoToUser(CreateUserDto createUserDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUpdateUserDto(UpdateUserDto updateUserDto, @MappingTarget User user);

}
