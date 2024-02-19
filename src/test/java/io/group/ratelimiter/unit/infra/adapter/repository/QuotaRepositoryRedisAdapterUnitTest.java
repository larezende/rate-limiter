package io.group.ratelimiter.unit.infra.adapter.repository;

import io.group.ratelimiter.infra.adapter.repository.QuotaRepositoryRedisAdapter;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import static io.group.ratelimiter.infra.adapter.repository.QuotaRepositoryRedisAdapter.QUOTA_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class QuotaRepositoryRedisAdapterUnitTest extends AbstractUnitTest {

    @Mock
    private StringRedisTemplate stringRedisTemplate;

    @Mock
    private HashOperations<String, String, Long> hashOperations;

    @InjectMocks
    private QuotaRepositoryRedisAdapter quotaRepositoryRedisAdapter;


    @Test
    void incrementQuota() {
        var userId = "1";
        var incremented = 1L;
        doReturn(hashOperations).when(stringRedisTemplate).opsForHash();
        doReturn(incremented).when(hashOperations).increment(QUOTA_KEY, userId, 1);

        var result = quotaRepositoryRedisAdapter.incrementQuota(userId);

        assertEquals(incremented, result.getUsage().longValue());
    }


}