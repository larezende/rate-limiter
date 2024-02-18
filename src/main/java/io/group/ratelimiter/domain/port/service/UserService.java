package io.group.ratelimiter.domain.port.service;

import io.group.ratelimiter.domain.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);

    void updateUserQuota(String id, int quota);

    List<User> getAllUsersQuota();

}
