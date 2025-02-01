package andrehsvictor.gonote.user;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import andrehsvictor.gonote.user.dto.PostUserDto;
import andrehsvictor.gonote.user.dto.PutUserDto;
import andrehsvictor.gonote.user.dto.GetUserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    GetUserDto userToGetUserDto(User user);

    User postUserDtoToUser(PostUserDto postUserDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromPutUserDto(PutUserDto putUserDto, @MappingTarget User user);

    @AfterMapping
    default void afterUpdateUserFromPutUserDto(PutUserDto putUserDto, @MappingTarget User user) {
        if (putUserDto.getAvatarUrl() != null && putUserDto.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(null);
        }
    }

}
