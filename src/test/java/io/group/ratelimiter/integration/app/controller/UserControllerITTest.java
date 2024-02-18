package io.group.ratelimiter.integration.app.controller;

import io.group.ratelimiter.app.dto.UpsertUserDto;
import io.group.ratelimiter.app.dto.UserDto;
import io.group.ratelimiter.integration.AbstractITTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerITTest extends AbstractITTest {

    @Test
    void shouldCreateUpdateGetAndDeleteUser() {
        var createUserDto = new UpsertUserDto("firstName", "lastName");
        var updateUserDto = new UpsertUserDto("firstName 2", "lastName 2");

        var createdUser = createUser(createUserDto);
        assertEquals(createUserDto.getFirstName(), createdUser.getFirstName());
        assertEquals(createUserDto.getLastName(), createdUser.getLastName());

        var userId = createdUser.getId();

        updateUser(userId, updateUserDto);

        var user = getUser(userId);
        assertEquals(updateUserDto.getFirstName(), user.getFirstName());
        assertEquals(updateUserDto.getLastName(), user.getLastName());

        deleteUser(userId);

        getUser404(userId);

    }

    private UserDto createUser(UpsertUserDto upsertUserDto) {
        return this.webClient.post()
            .uri("/user")
            .bodyValue(upsertUserDto)
            .exchange()
            .expectStatus().isOk()
            .returnResult(UserDto.class)
            .getResponseBody().blockFirst();
    }

    private UserDto getUser(String userId) {
        return this.webClient.get()
            .uri("/user/" + userId)
            .exchange()
            .expectStatus().isOk()
            .returnResult(UserDto.class)
            .getResponseBody().blockFirst();
    }

    private void getUser404(String userId) {
        this.webClient.get()
            .uri("/user/" + userId)
            .exchange()
            .expectStatus().isNotFound();
    }

    private void updateUser(String userId, UpsertUserDto upsertUserDto) {
        this.webClient.put()
            .uri("/user/" + userId)
            .bodyValue(upsertUserDto)
            .exchange()
            .expectStatus().isOk();
    }

    private void deleteUser(String userId) {
        this.webClient.delete()
            .uri("/user/" + userId)
            .exchange()
            .expectStatus().isOk();
    }

}
