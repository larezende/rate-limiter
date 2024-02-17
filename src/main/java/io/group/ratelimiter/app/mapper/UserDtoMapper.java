package io.group.ratelimiter.app.mapper;

import io.group.ratelimiter.app.dto.UpsertUserDto;
import io.group.ratelimiter.app.dto.UserDto;
import io.group.ratelimiter.domain.model.User;

public class UserDtoMapper {

    private UserDtoMapper() {
    }

    public static User toUser(UpsertUserDto upsertUserDto) {
        User user = new User();
        user.setFirstName(upsertUserDto.getFirstName());
        user.setLastName(upsertUserDto.getLastName());
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLastLoginTimeUtc(user.getLastLoginTimeUtc());
        return userDto;
    }


}
