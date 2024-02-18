package io.group.ratelimiter.infra.adapter.repository;

import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.port.repository.UserRepository;
import io.group.ratelimiter.infra.jpa.UserJpaRepository;
import io.group.ratelimiter.infra.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryMysqlAdapter implements UserRepository {

    private final UserJpaRepository repository;

    @Override
    public User createUser(User user) {
        return saveUser(user);
    }

    @Override
    public Optional<User> getUser(String id) {
        var userEntity = repository.findById(id);
        return userEntity.map(UserEntityMapper::toUser);
    }

    @Override
    public User updateUser(User user) {
        return saveUser(user);
    }

    @Override
    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getAllUsersQuotas() {
        var usersQuotas = repository.findAllUsersQuotas();
        return usersQuotas.stream().map(UserEntityMapper::toUser).toList();
    }

    private User saveUser(User user) {
        var userEntity = UserEntityMapper.toUserEntity(user);
        var savedUserEntity = repository.save(userEntity);
        return UserEntityMapper.toUser(savedUserEntity);
    }

}
