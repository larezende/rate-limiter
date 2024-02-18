package io.group.ratelimiter.app.controller;


import io.group.ratelimiter.app.dto.ErrorDto;
import io.group.ratelimiter.domain.exception.NotFoundException;
import io.group.ratelimiter.domain.exception.QuotaExceededException;
import io.group.ratelimiter.infra.exception.RepositoryUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundException(final NotFoundException exception) {
        log.info(exception.getMessage(), exception);
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepositoryUnavailableException.class)
    public ResponseEntity<ErrorDto> repositoryUnavailableException(final RepositoryUnavailableException exception) {
        log.error(exception.getMessage(), exception);
        return buildResponse(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(QuotaExceededException.class)
    public ResponseEntity<ErrorDto> quotaExceededException(final QuotaExceededException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    private ResponseEntity<ErrorDto> buildResponse(final String message, final HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
            .body(ErrorDto.builder().message(message).dateTime(Instant.now()).build());
    }


}
