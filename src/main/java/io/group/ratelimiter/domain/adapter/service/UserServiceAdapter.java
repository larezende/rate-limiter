package io.group.ratelimiter.domain.adapter.service;

import io.group.ratelimiter.domain.exception.NotFoundException;
import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.port.provider.UserRepositoryProvider;
import io.group.ratelimiter.domain.port.repository.UserRepository;
import io.group.ratelimiter.domain.port.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceAdapter implements UserService {

    public static final String USER_S_NOT_FOUND = "User %s not found";
    private final UserRepositoryProvider userRepositoryProvider;

    @Override
    public User createUser(User user) {
        var repository = getRepository();
        user.setId(UUID.randomUUID().toString());
        return repository.createUser(user);
    }

    @Override
    public User getUser(String id) {
        var repository = getRepository();
        return repository.getUser(id).orElseThrow(() -> new NotFoundException(String.format(USER_S_NOT_FOUND, id)));
    }

    @Override
    public User updateUser(String id, User user) {
        var repository = getRepository();
        var userToUpdate = repository.getUser(id)
            .map(u -> {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                return u;
            }).orElseThrow(() -> new NotFoundException(String.format(USER_S_NOT_FOUND, id)));

        return repository.updateUser(userToUpdate);
    }

    @Override
    public void deleteUser(String id) {
        var repository = getRepository();
        repository.deleteUser(id);
    }

    @Override
    public void updateUserQuota(String id, int quota) {
        var repository = getRepository();
        var user = repository.getUser(id).orElseThrow(() -> new NotFoundException(String.format(USER_S_NOT_FOUND, id)));
        user.setLastLoginTimeUtc(LocalDateTime.now());
        user.setCurrentQuota(quota);
        repository.updateUser(user);
    }

    @Override
    public List<User> getAllUsersQuota() {
        var repository = getRepository();
        return repository.getAllUsersQuota();
    }

    private UserRepository getRepository() {
        return userRepositoryProvider.getRepository();
    }

}
