package io.group.ratelimiter.domain.port.service;

import io.group.ratelimiter.domain.model.User;

public interface UserService {

    User createUser(User user);

    User getUser(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);

}
