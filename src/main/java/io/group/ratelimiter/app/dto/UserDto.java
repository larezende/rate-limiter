package io.group.ratelimiter.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLoginTimeUtc;

}
