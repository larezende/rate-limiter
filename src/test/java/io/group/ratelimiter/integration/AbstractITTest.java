package io.group.ratelimiter.integration;

import com.redis.testcontainers.RedisContainer;
import io.group.ratelimiter.domain.model.User;
import io.group.ratelimiter.domain.port.service.UserService;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("it-test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ITTestConfig.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@Tag("IT")
@Testcontainers
public abstract class AbstractITTest {

    @Container
    public static final RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:7-alpine")).withExposedPorts(6379);
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    @Autowired
    protected UserService userService;
    @Autowired
    protected WebTestClient webClient;

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379));
    }

    protected User getTestUser() {
        return User.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .currentQuota(0)
            .build();
    }

    protected User createUser() {
        return userService.createUser(getTestUser());
    }

}
