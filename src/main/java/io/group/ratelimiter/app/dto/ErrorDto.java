package io.group.ratelimiter.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ErrorDto {

    private final String message;

    private final Instant dateTime;

}
