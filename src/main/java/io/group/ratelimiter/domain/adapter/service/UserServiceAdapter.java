package io.group.ratelimiter.domain.adapter.service;

import io.group.ratelimiter.domain.exception.NotFoundException;
import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.port.provider.UserRepositoryProvider;
import io.group.ratelimiter.domain.port.repository.UserRepository;
import io.group.ratelimiter.domain.port.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceAdapter implements UserService {

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
        return repository.getUser(id).orElseThrow(() -> new NotFoundException(String.format("User %s not found", id)));
    }

    @Override
    public User updateUser(String id, User user) {
        var repository = getRepository();
        var userToUpdate = repository.getUser(id)
            .map(u -> {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                return u;
            }).orElseThrow(() -> new NotFoundException(String.format("User %s not found", id)));

        return repository.updateUser(userToUpdate);
    }

    @Override
    public void deleteUser(String id) {
        var repository = getRepository();
        repository.deleteUser(id);
    }

    private UserRepository getRepository() {
        return userRepositoryProvider.getRepository();
    }

}
