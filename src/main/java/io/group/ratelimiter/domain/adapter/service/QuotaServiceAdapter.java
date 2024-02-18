package io.group.ratelimiter.domain.adapter.service;

import io.group.ratelimiter.domain.exception.QuotaExceededException;
import io.group.ratelimiter.domain.model.UserQuota;
import io.group.ratelimiter.domain.port.repository.QuotaRepository;
import io.group.ratelimiter.domain.port.service.QuotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuotaServiceAdapter implements QuotaService {

    private final QuotaRepository quotaRepository;

    @Value("${maximum-quota}")
    private int maxQuota;

    @Override
    public UserQuota consumeQuota(String userId) {
        var current = quotaRepository.getQuota(userId);
        if (current.getUsage() >= maxQuota) {
            throw new QuotaExceededException("Quota exceeded");
        }
        return quotaRepository.incrementQuota(userId);
    }

}
