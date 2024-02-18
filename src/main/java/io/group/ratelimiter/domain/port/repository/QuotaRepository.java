package io.group.ratelimiter.domain.port.repository;

import io.group.ratelimiter.domain.model.UserQuota;

public interface QuotaRepository {

    UserQuota incrementQuota(String userId);

}
