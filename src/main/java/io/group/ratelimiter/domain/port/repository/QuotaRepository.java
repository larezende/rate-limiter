package io.group.ratelimiter.domain.port.repository;

import io.group.ratelimiter.domain.model.UserQuota;

public interface QuotaRepository {

    UserQuota getQuota(String userId);


    UserQuota incrementQuota(String userId);

}
