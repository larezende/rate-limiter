package io.group.ratelimiter.unit.infra.mapper;

import io.group.ratelimiter.infra.mapper.UserEntityMapper;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserEntityMapperUnitTest extends AbstractUnitTest {

    @Test
    void toUser_shouldConvertToUser_whenUserEntityIsGiven() {
        var userEntity = getTestUserEntity();

        var user = UserEntityMapper.toUser(userEntity);

        assertNotNull(user);
        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getLastLoginTimeUtc(), user.getLastLoginTimeUtc());
        assertEquals(userEntity.getCurrentQuota(), user.getCurrentQuota());
    }

    @Test
    void toUserEntity_shouldConvertToUserEntity_whenUserIsGiven() {
        var user = getTestUser();

        var userEntity = UserEntityMapper.toUserEntity(user);

        assertNotNull(userEntity);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getFirstName(), userEntity.getFirstName());
        assertEquals(user.getLastName(), userEntity.getLastName());
        assertEquals(user.getLastLoginTimeUtc(), userEntity.getLastLoginTimeUtc());
        assertEquals(user.getCurrentQuota(), userEntity.getCurrentQuota());
    }


}