package io.group.ratelimiter.unit.app.controller;

import io.group.ratelimiter.unit.AbstractUnitTest;
import io.group.ratelimiter.app.controller.QuotaController;
import io.group.ratelimiter.domain.port.service.QuotaService;
import io.group.ratelimiter.domain.port.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class QuotaControllerUnitTest extends AbstractUnitTest {

    @Mock
    private QuotaService quotaService;

    @Mock
    private UserService userService;

    @InjectMocks
    private QuotaController quotaController;

    @Test
    void consumeQuota_shouldReturnOk_whenUserIdIsProvided() {
        var userQuota = getTestUserQuota();
        doReturn(userQuota).when(quotaService).consumeQuota(USER_ID);

        var response = quotaController.consumeQuota(USER_ID);

        verify(userService).updateUserQuota(USER_ID, userQuota.getUsage());
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USER_ID, body.getUserId());
        assertEquals(userQuota.getUsage(), body.getUsage());
    }

    @Test
    void getUsersQuota_shouldReturnUsersQuotas_whenCalled() {
        var user = getTestUser();
        doReturn(List.of(user)).when(userService).getAllUsersQuotas();

        var response = quotaController.getUsersQuotas();

        var body = response.getBody();

        assertNotNull(body);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, body.size());
        assertEquals(USER_ID, body.get(0).getUserId());
        assertEquals(user.getCurrentQuota(), body.get(0).getUsage());
    }

}