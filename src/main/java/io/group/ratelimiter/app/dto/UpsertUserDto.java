package io.group.ratelimiter.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpsertUserDto {

    private String firstName;
    private String lastName;

}
