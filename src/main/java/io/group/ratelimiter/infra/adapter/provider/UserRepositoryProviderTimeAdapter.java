package io.group.ratelimiter.infra.adapter.provider;

import io.group.ratelimiter.domain.port.provider.UserRepositoryProvider;
import io.group.ratelimiter.domain.port.repository.UserRepository;
import io.group.ratelimiter.infra.adapter.repository.UserRepositoryElasticAdapter;
import io.group.ratelimiter.infra.adapter.repository.UserRepositoryMysqlAdapter;
import io.group.ratelimiter.infra.exception.RepositoryUnavailableException;
import io.group.ratelimiter.infra.model.TimeSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class UserRepositoryProviderTimeAdapter implements UserRepositoryProvider {

    private final UserRepositoryMysqlAdapter userMysqlRepository;

    private final UserRepositoryElasticAdapter userElasticRepository;

    @Qualifier("mysqlTimeSchedule")
    private final TimeSchedule mysqlTimeSchedule;

    @Qualifier("elasticTimeSchedule")
    private final TimeSchedule elasticTimeSchedule;

    @Override
    public UserRepository getRepository() throws RepositoryUnavailableException {
        LocalTime now = LocalTime.now();

        if (mysqlTimeSchedule.isTimeInSchedule(now)) {
            return userMysqlRepository;
        } else if (elasticTimeSchedule.isTimeInSchedule(now)) {
            return userElasticRepository;
        }

        throw new RepositoryUnavailableException("No repository available this time");
    }

}
