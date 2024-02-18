package io.group.ratelimiter.unit.domain.adapter.service;

import io.group.ratelimiter.domain.adapter.service.UserServiceAdapter;
import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.port.provider.UserRepositoryProvider;
import io.group.ratelimiter.domain.port.repository.UserRepository;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class UserServiceAdapterUnitTest extends AbstractUnitTest {

    private static final int QUOTA = 3;

    @Mock
    private UserRepositoryProvider userRepositoryProvider;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceAdapter userServiceAdapter;

    @BeforeEach
    void setup() {
        doReturn(userRepository).when(userRepositoryProvider).getRepository();
    }

    @Test
    void createUser_shouldReturnUser_whenUserIsCreated() {
        User testUser = getTestUser();
        userServiceAdapter.createUser(testUser);
        verify(userRepository).createUser(testUser);
        assertNotEquals(USER_ID, testUser.getId());
    }

    @Test
    void getUser_shouldReturnUser_whenUserExists() {
        doReturn(Optional.of(getTestUser())).when(userRepository).getUser(USER_ID);

        var user = userServiceAdapter.getUser(USER_ID);

        assertEquals(USER_ID, user.getId());
    }

    @Test
    void getUser_shouldThrowNotFoundException_whenUserDoesNotExist() {
        doReturn(Optional.empty()).when(userRepository).getUser(USER_ID);

        assertThrows(Exception.class, () -> userServiceAdapter.getUser(USER_ID));
    }

    @Test
    void updateUser_shouldReturnUser_whenUserIsUpdated() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        doReturn(Optional.of(getEmptyUser())).when(userRepository).getUser(USER_ID);
        doReturn(getTestUser()).when(userRepository).updateUser(any());

        userServiceAdapter.updateUser(USER_ID, getTestUser());

        verify(userRepository).updateUser(userCaptor.capture());

        var user = userCaptor.getValue();

        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
    }

    @Test
    void updateUser_shouldThrowNotFoundException_whenUserDoesNotExist() {
        doReturn(Optional.empty()).when(userRepository).getUser(USER_ID);

        assertThrows(Exception.class, () -> userServiceAdapter.updateUser(USER_ID, getTestUser()));
    }

    @Test
    void deleteUser_shouldCallRepository_whenUserIsDeleted() {
        userServiceAdapter.deleteUser(USER_ID);
        verify(userRepository).deleteUser(USER_ID);
    }

    @Test
    void updateUserQuota_shouldCallRepository_whenUserQuotaIsUpdated() {
        User testUser = getEmptyUser();
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        doReturn(Optional.of(testUser)).when(userRepository).getUser(USER_ID);

        userServiceAdapter.updateUserQuota(USER_ID, QUOTA);

        verify(userRepository).updateUser(userCaptor.capture());

        var user = userCaptor.getValue();

        assertEquals(QUOTA, user.getCurrentQuota());
        assertNotNull(user.getLastLoginTimeUtc());
    }

    @Test
    void updateUserQuota_shouldThrowNotFoundException_whenUserDoesNotExist() {
        doReturn(Optional.empty()).when(userRepository).getUser(USER_ID);

        assertThrows(Exception.class, () -> userServiceAdapter.updateUserQuota(USER_ID, QUOTA));
    }

    @Test
    void getAllUsersQuotas_shouldReturnUsers_whenUsersExist() {
        var user = getTestUser();
        doReturn(List.of(user)).when(userRepository).getAllUsersQuotas();
        var users = userServiceAdapter.getAllUsersQuotas();
        assertEquals(1, users.size());
        assertTrue(users.contains(user));
    }

}