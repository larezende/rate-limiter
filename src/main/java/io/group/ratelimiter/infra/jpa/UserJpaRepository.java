package io.group.ratelimiter.infra.jpa;

import io.group.ratelimiter.infra.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

}
