package io.group.ratelimiter.domain.port.service;

import io.group.ratelimiter.domain.model.UserQuota;

public interface QuotaService {

    UserQuota consumeQuota(String userId);

}
