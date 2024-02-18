package io.group.ratelimiter.infra.adapter.repository;

import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.port.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserRepositoryElasticAdapter implements UserRepository {

    @Override
    public User createUser(User user) {
        log.info("createUser({})", user);
        return user;
    }

    @Override
    public Optional<User> getUser(String id) {
        log.info("getUser({})", id);
        var dummyUser = User.builder()
            .id(id)
            .firstName("John")
            .lastName("Doe")
            .build();
        return Optional.of(dummyUser);
    }

    @Override
    public User updateUser(User user) {
        log.info("updateUser({})", user);
        return user;
    }

    @Override
    public void deleteUser(String id) {
        log.info("deleteUser({})", id);
    }

    @Override
    public List<User> getAllUsersQuotas() {
        return List.of(User.builder()
            .id("id")
            .firstName("John")
            .lastName("Doe")
            .build());
    }

}
