package io.group.ratelimiter.app.controller;

import io.group.ratelimiter.app.dto.UpsertUserDto;
import io.group.ratelimiter.app.dto.UserDto;
import io.group.ratelimiter.app.mapper.UserDtoMapper;
import io.group.ratelimiter.domain.port.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody final UpsertUserDto upsertUserDto) {
        final var user = UserDtoMapper.toUser(upsertUserDto);
        final var createdUser = userService.createUser(user);
        final var createdUserDto = UserDtoMapper.toUserDto(createdUser);
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") final String userId) {
        final var user = userService.getUser(userId);
        final var userDto = UserDtoMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") final String userId, @RequestBody final UpsertUserDto upsertUserDto) {
        var user = UserDtoMapper.toUser(upsertUserDto);
        var updatedUser = userService.updateUser(userId, user);
        var updatedUserDto = UserDtoMapper.toUserDto(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") final String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
