package io.group.ratelimiter.unit.app.mapper;

import io.group.ratelimiter.app.mapper.UserDtoMapper;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserDtoMapperUnitTest extends AbstractUnitTest {

    @Test
    void toUser_shouldReturnUser_whenGivenUpsertUserDto() {
        var upsertUserDto = getTestUpsertUserDto();

        var result = UserDtoMapper.toUser(upsertUserDto);

        assertNotNull(result);
        assertEquals(upsertUserDto.getFirstName(), result.getFirstName());
        assertEquals(upsertUserDto.getLastName(), result.getLastName());
    }

    @Test
    void toUserDto_shouldReturnUserDto_whenGivenUser() {
        var user = getTestUser();

        var result = UserDtoMapper.toUserDto(user);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getLastLoginTimeUtc(), result.getLastLoginTimeUtc());
        assertEquals(user.getCurrentQuota(), result.getCurrentQuota());
    }

}