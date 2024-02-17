package io.group.ratelimiter.infra.provider;

import io.group.ratelimiter.domain.port.repository.UserRepository;
import io.group.ratelimiter.infra.adapter.repository.UserRepositoryElasticAdapter;
import io.group.ratelimiter.infra.adapter.repository.UserRepositoryMysqlAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class UserRepositoryProvider {

    private UserRepositoryElasticAdapter userElasticRepository;

    private UserRepositoryMysqlAdapter userMysqlRepository;

    public UserRepository getRepository() {
        if (new Random().nextBoolean()) {
            return userMysqlRepository;
        } else {
            return userElasticRepository;
        }
    }

}
