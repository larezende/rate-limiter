package io.group.ratelimiter.infra.mapper;

import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.infra.model.UserEntity;

public class UserEntityMapper {

    private UserEntityMapper() {
    }

    public static User toUser(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .firstName(userEntity.getFirstName())
            .lastName(userEntity.getLastName())
            .lastLoginTimeUtc(userEntity.getLastLoginTimeUtc())
            .build();
    }

    public static UserEntity toUserEntity(User user) {
        return UserEntity.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .lastLoginTimeUtc(user.getLastLoginTimeUtc())
            .build();
    }

}
