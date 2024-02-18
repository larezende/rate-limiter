package io.group.ratelimiter.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQuota {

    private String userId;
    private int usage;

}
