package io.group.ratelimiter.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQuotaDto {

    private String userId;
    private Integer usage;

}
