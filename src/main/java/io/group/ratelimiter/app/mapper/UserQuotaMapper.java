package io.group.ratelimiter.app.mapper;

import io.group.ratelimiter.app.dto.UserQuotaDto;
import io.group.ratelimiter.domain.model.User;

public class UserQuotaMapper {

    private UserQuotaMapper() {
    }

    public static UserQuotaDto toUserQuotaDto(User user) {
        return new UserQuotaDto(user.getId(), user.getCurrentQuota());
    }

}
