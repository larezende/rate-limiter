package io.group.ratelimiter.unit.app.controller;

import io.group.ratelimiter.app.controller.UserController;
import io.group.ratelimiter.domain.port.service.UserService;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class UserControllerUnitTest extends AbstractUnitTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser_shouldCreateUser_whenGivenUpsertDto() {
        var dto = getTestUpsertUserDto();
        var user = getTestUser();
        when(userService.createUser(any())).thenReturn(user);

        var result = userController.createUser(dto);
        var body = result.getBody();

        assertNotNull(body);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(body.getId());
        assertEquals(user.getFirstName(), body.getFirstName());
    }

    @Test
    void getUser_shouldReturnUser_whenGivenUserId() {
        var user = getTestUser();
        when(userService.getUser(user.getId())).thenReturn(user);

        var result = userController.getUser(user.getId());
        var body = result.getBody();

        assertNotNull(body);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(user.getId(), body.getId());
        assertEquals(user.getFirstName(), body.getFirstName());
    }

    @Test
    void updateUser_shouldReturnUpdatedUser_whenGivenUserIdAndUpsertDto() {
        var user = getTestUser();
        var dto = getTestUpsertUserDto();
        when(userService.updateUser(eq(user.getId()), any())).thenReturn(user);

        var result = userController.updateUser(user.getId(), dto);
        var body = result.getBody();

        assertNotNull(body);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(user.getId(), body.getId());
        assertEquals(user.getFirstName(), body.getFirstName());
    }

    @Test
    void deleteUser_shouldDeleteUser_whenGivenUserId() {
        var userId = USER_ID;
        var result = userController.deleteUser(userId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}