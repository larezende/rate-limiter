package io.group.ratelimiter.infra.adapter.repository;

import io.group.ratelimiter.domain.model.UserQuota;
import io.group.ratelimiter.domain.port.repository.QuotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuotaRepositoryRedisAdapter implements QuotaRepository {

    private static final String QUOTA_KEY = "quota";

    final StringRedisTemplate stringRedisTemplate;

    @Override
    public UserQuota getQuota(String userId) {
        var currentQuota = (String) stringRedisTemplate.opsForHash().get(QUOTA_KEY, userId);
        if (currentQuota == null) {
            return new UserQuota(userId, 0);
        }
        return new UserQuota(userId, Integer.parseInt(currentQuota));
    }

    @Override
    public UserQuota incrementQuota(String userId) {
        var currentQuota = stringRedisTemplate.opsForHash().increment(QUOTA_KEY, userId, 1);
        return new UserQuota(userId, currentQuota.intValue());
    }

}
