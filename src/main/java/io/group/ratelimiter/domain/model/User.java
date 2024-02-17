package io.group.ratelimiter.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {

    private UUID id;

    private String firstName;

    private String lastName;

    private LocalDateTime lastLoginTimeUtc;

}
