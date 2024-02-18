package io.group.ratelimiter.integration.app.controller;

import io.group.ratelimiter.app.dto.UserQuotaDto;
import io.group.ratelimiter.integration.AbstractITTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuotaControllerITTest extends AbstractITTest {

    @Test
    void testQuotaUsageUntilTooManyRequests() {
        var user = createUser();

        UserQuotaDto result;

        result = consumeQuotaOk(user.getId());

        assertNotNull(result);
        assertEquals(user.getId(), result.getUserId());
        assertEquals(1, result.getUsage());

        result = consumeQuotaOk(user.getId());
        assertNotNull(result);
        assertEquals(2, result.getUsage());

        result = consumeQuotaOk(user.getId());
        assertNotNull(result);
        assertEquals(3, result.getUsage());

        result = consumeQuotaOk(user.getId());
        assertNotNull(result);
        assertEquals(4, result.getUsage());

        result = consumeQuotaOk(user.getId());
        assertNotNull(result);
        assertEquals(5, result.getUsage());

        consumeQuota(user.getId())
            .expectStatus().isEqualTo(HttpStatus.TOO_MANY_REQUESTS);

        consumeQuota(user.getId())
            .expectStatus().isEqualTo(HttpStatus.TOO_MANY_REQUESTS);

    }

    @Test
    void shouldListAllQuotas() {
        var user1 = createUser();
        var user2 = createUser();
        var user3 = createUser();

        consumeQuotaOk(user1.getId());
        consumeQuotaOk(user2.getId());
        consumeQuotaOk(user2.getId());
        consumeQuotaOk(user3.getId());
        consumeQuotaOk(user3.getId());
        consumeQuotaOk(user3.getId());

        var quotas = this.webClient.get()
            .uri("/quota")
            .exchange()
            .expectStatus().isOk()
            .returnResult(UserQuotaDto.class)
            .getResponseBody()
            .collectList()
            .block();

        assertNotNull(quotas);

        var user1Quota = quotas.stream().filter(q -> q.getUserId().equals(user1.getId())).findFirst().orElseThrow();
        var user2Quota = quotas.stream().filter(q -> q.getUserId().equals(user2.getId())).findFirst().orElseThrow();
        var user3Quota = quotas.stream().filter(q -> q.getUserId().equals(user3.getId())).findFirst().orElseThrow();

        assertEquals(1, user1Quota.getUsage());
        assertEquals(2, user2Quota.getUsage());
        assertEquals(3, user3Quota.getUsage());

    }

    private ResponseSpec consumeQuota(String userId) {
        return this.webClient.post()
            .uri("/quota/" + userId)
            .exchange();
    }

    private UserQuotaDto consumeQuotaOk(String userId) {
        return consumeQuota(userId)
            .expectStatus().isOk()
            .returnResult(UserQuotaDto.class)
            .getResponseBody().blockFirst();
    }

}