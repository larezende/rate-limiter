package io.group.ratelimiter.unit.infra.adapter.repository;

import io.group.ratelimiter.infra.adapter.repository.UserRepositoryElasticAdapter;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryElasticAdapterUnitTest extends AbstractUnitTest {

    private final UserRepositoryElasticAdapter userRepositoryElasticAdapter = new UserRepositoryElasticAdapter();


    @Test
    void createUser_shouldReturnSuccess_whenItsOk() {
        var user = getTestUser();
        var result = userRepositoryElasticAdapter.createUser(user);
        assertEquals(user, result);
    }

    @Test
    void getUser_shouldReturnSuccess_whenItsOk() {
        var id = "abc";
        var result = userRepositoryElasticAdapter.getUser(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void updateUser_shouldReturnSuccess_whenItsOk() {
        var user = getTestUser();
        var result = userRepositoryElasticAdapter.updateUser(user);
        assertEquals(user, result);
    }

    @Test
    void deleteUser_shouldReturnSuccess_whenItsOk() {
        var id = "abc";
        assertDoesNotThrow(() -> userRepositoryElasticAdapter.deleteUser(id));
    }

    @Test
    void getAllUsersQuotas_shouldReturnSuccess_whenItsOk() {
        var result = userRepositoryElasticAdapter.getAllUsersQuotas();
        assertFalse(result.isEmpty());
    }

}