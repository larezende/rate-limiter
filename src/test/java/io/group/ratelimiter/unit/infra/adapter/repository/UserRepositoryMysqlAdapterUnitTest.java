package io.group.ratelimiter.unit.infra.adapter.repository;

import io.group.ratelimiter.infra.adapter.repository.UserRepositoryMysqlAdapter;
import io.group.ratelimiter.infra.jpa.UserJpaRepository;
import io.group.ratelimiter.infra.model.UserEntity;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRepositoryMysqlAdapterUnitTest extends AbstractUnitTest {

    @Mock
    private UserJpaRepository repository;

    @InjectMocks
    private UserRepositoryMysqlAdapter userRepositoryMysqlAdapter;

    @Test
    void createUser_shouldReturnUser_whenUserIsCreated() {
        var user = getTestUser();
        when(repository.save(any())).thenReturn(getTestUserEntity());
        var createdUser = userRepositoryMysqlAdapter.createUser(user);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getLastLoginTimeUtc(), createdUser.getLastLoginTimeUtc());
    }

    @Test
    void getUser_shouldReturnUser_whenUserExists() {
        var user = getTestUserEntity();
        when(repository.findById(any())).thenReturn(java.util.Optional.of(user));
        var foundUser = userRepositoryMysqlAdapter.getUser(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
        assertEquals(user.getLastLoginTimeUtc(), foundUser.get().getLastLoginTimeUtc());
    }

    @Test
    void updateUser_shouldReturnUser_whenUserIsUpdated() {
        var user = getTestUser();
        when(repository.save(any())).thenReturn(getTestUserEntity());
        var updatedUser = userRepositoryMysqlAdapter.updateUser(user);
        assertEquals(user.getId(), updatedUser.getId());
        assertEquals(user.getLastLoginTimeUtc(), updatedUser.getLastLoginTimeUtc());
    }

    @Test
    void deleteUser_shouldNotThrowException_whenUserExists() {
        var id = "abc";
        userRepositoryMysqlAdapter.deleteUser(id);
        verify(repository).deleteById(id);
    }

    @Test
    void getAllUsersQuotas_shouldReturnListOfUsers_whenUsersExist() {
        UserEntity testUserEntity = getTestUserEntity();
        when(repository.findAllUsersQuotas()).thenReturn(List.of(testUserEntity));
        var users = userRepositoryMysqlAdapter.getAllUsersQuotas();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(testUserEntity.getId(), users.get(0).getId());
        assertEquals(testUserEntity.getLastLoginTimeUtc(), users.get(0).getLastLoginTimeUtc());
    }


}