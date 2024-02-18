package io.group.ratelimiter.unit.app.mapper;

import io.group.ratelimiter.app.mapper.UserQuotaMapper;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserQuotaMapperUnitTest extends AbstractUnitTest {

    @Test
    void toUserQuotaDto_shouldReturnUserQuotaDto_whenGivenUser() {
        var user = getTestUser();
        var userQuotaDto = UserQuotaMapper.toUserQuotaDto(user);
        assertEquals(user.getId(), userQuotaDto.getUserId());
        assertEquals(user.getCurrentQuota(), userQuotaDto.getUsage());
    }

}