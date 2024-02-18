package io.group.ratelimiter.app.controller;

import io.group.ratelimiter.app.dto.UserQuotaDto;
import io.group.ratelimiter.app.mapper.UserQuotaMapper;
import io.group.ratelimiter.domain.port.service.QuotaService;
import io.group.ratelimiter.domain.port.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quota")
@RequiredArgsConstructor
public class QuotaController {

    private final QuotaService quotaService;

    private final UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserQuotaDto> consumeQuota(@PathVariable final String userId) {
        var currentQuota = quotaService.consumeQuota(userId);
        userService.updateUserQuota(userId, currentQuota.getUsage());
        return ResponseEntity.status(HttpStatus.OK).body(new UserQuotaDto(userId, currentQuota.getUsage()));
    }

    @GetMapping
    public ResponseEntity<List<UserQuotaDto>> getUsersQuotas() {
        var usersQuotas = userService.getAllUsersQuotas();
        var usersQuotasDtos = usersQuotas.stream().map(UserQuotaMapper::toUserQuotaDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(usersQuotasDtos);
    }

}
