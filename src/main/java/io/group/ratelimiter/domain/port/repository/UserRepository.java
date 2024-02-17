package io.group.ratelimiter.domain.port.repository;

import io.group.ratelimiter.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    Optional<User> getUser(String id);

    User updateUser(User user);

    void deleteUser(String id);

}
