package io.group.ratelimiter.unit.app.controller;

import io.group.ratelimiter.app.controller.ControllerAdvice;
import io.group.ratelimiter.app.dto.ErrorDto;
import io.group.ratelimiter.domain.exception.NotFoundException;
import io.group.ratelimiter.domain.exception.QuotaExceededException;
import io.group.ratelimiter.infra.exception.RepositoryUnavailableException;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ControllerAdviceUnitTest extends AbstractUnitTest {

    private final ControllerAdvice controllerAdvice = new ControllerAdvice();

    @Test
    void notFoundException_shouldHandleException_whenNotFoundException() {
        var exception = new NotFoundException("Not found");
        var result = controllerAdvice.notFoundException(exception);
        assertResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND, result);
    }

    @Test
    void repositoryUnavailableException_shouldHandleException_whenRepositoryUnavailableException() {
        var exception = new RepositoryUnavailableException("unavailable");
        var result = controllerAdvice.repositoryUnavailableException(exception);
        assertResponseEntity(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, result);
    }

    @Test
    void quotaExceededException_shouldHandleException_whenQuotaExceededException() {
        var exception = new QuotaExceededException("quota exceeded");
        var result = controllerAdvice.quotaExceededException(exception);
        assertResponseEntity(exception.getMessage(), HttpStatus.TOO_MANY_REQUESTS, result);
    }

    private void assertResponseEntity(final String message, final HttpStatus httpStatus, final ResponseEntity<ErrorDto> result) {
        var body = result.getBody();
        assertNotNull(body);
        assertEquals(httpStatus, result.getStatusCode());
        assertEquals(message, result.getBody().getMessage());
    }

}