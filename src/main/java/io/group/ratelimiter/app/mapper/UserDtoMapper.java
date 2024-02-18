package io.group.ratelimiter.app.mapper;

import io.group.ratelimiter.app.dto.UpsertUserDto;
import io.group.ratelimiter.app.dto.UserDto;
import io.group.ratelimiter.domain.model.User;

public class UserDtoMapper {

    private UserDtoMapper() {
    }

    public static User toUser(UpsertUserDto upsertUserDto) {
        return User.builder()
            .firstName(upsertUserDto.getFirstName())
            .lastName(upsertUserDto.getLastName())
            .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .lastLoginTimeUtc(user.getLastLoginTimeUtc())
            .currentQuota(user.getCurrentQuota())
            .build();
    }


}
