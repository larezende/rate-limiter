package io.group.ratelimiter.domain.port.provider;

import io.group.ratelimiter.domain.port.repository.UserRepository;

public interface UserRepositoryProvider {

    UserRepository getRepository();

}
