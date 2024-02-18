package io.group.ratelimiter.unit.domain.adapter.service;

import io.group.ratelimiter.domain.adapter.service.QuotaServiceAdapter;
import io.group.ratelimiter.domain.exception.QuotaExceededException;
import io.group.ratelimiter.domain.model.UserQuota;
import io.group.ratelimiter.domain.port.repository.QuotaRepository;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

class QuotaServiceAdapterUnitTest extends AbstractUnitTest {

    public static final String USER_ID = "userId";
    public static final int MAX_QUOTA = 5;
    private QuotaServiceAdapter quotaServiceAdapter;

    @Mock
    private QuotaRepository quotaRepository;

    @BeforeEach
    void setup() {
        quotaServiceAdapter = new QuotaServiceAdapter(quotaRepository, MAX_QUOTA);
    }

    @Test
    void consumeQuota_shouldReturnQuota_whenQuotaIsNotExceeded() {
        doReturn(new UserQuota(USER_ID, 1)).when(quotaRepository).incrementQuota(USER_ID);

        var userQuota = quotaServiceAdapter.consumeQuota(USER_ID);

        assertEquals(1, userQuota.getUsage());
        assertEquals(USER_ID, userQuota.getUserId());
    }

    @Test
    void consumeQuota_shouldThrowQuotaExceededException_whenQuotaIsExceeded() {
        doReturn(new UserQuota(USER_ID, 10)).when(quotaRepository).incrementQuota(USER_ID);

        assertThrows(QuotaExceededException.class, () -> quotaServiceAdapter.consumeQuota(USER_ID));
    }


}