package io.group.ratelimiter.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String firstName;

    private String lastName;

    private LocalDateTime lastLoginTimeUtc;

}
