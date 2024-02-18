package io.group.ratelimiter.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("it-test")
@EnableAutoConfiguration()
@ComponentScan(basePackages = {"io.group.ratelimiter"})
public class ITTestConfig {

}
