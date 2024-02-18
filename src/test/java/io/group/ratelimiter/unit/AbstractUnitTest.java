package io.group.ratelimiter.unit;

import io.group.ratelimiter.app.dto.UpsertUserDto;
import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.model.UserQuota;
import io.group.ratelimiter.infra.model.UserEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public abstract class AbstractUnitTest {

    public static final String USER_ID = "userId";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final LocalDateTime NOW = LocalDateTime.now();
    public static final int USAGE = 1;

    protected User getEmptyUser() {
        return User.builder().build();
    }

    protected User getTestUser() {
        return User.builder()
            .id(USER_ID)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .lastLoginTimeUtc(NOW)
            .currentQuota(0)
            .build();
    }

    protected UserEntity getTestUserEntity() {
        return UserEntity.builder()
            .id(USER_ID)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .lastLoginTimeUtc(NOW)
            .currentQuota(0)
            .build();
    }

    protected UpsertUserDto getTestUpsertUserDto() {
        return new UpsertUserDto(FIRST_NAME, LAST_NAME);
    }

    protected UserQuota getTestUserQuota() {
        return new UserQuota(USER_ID, USAGE);
    }

}
