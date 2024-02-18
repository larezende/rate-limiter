package io.group.ratelimiter.infra.jpa;

import io.group.ratelimiter.infra.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    @Query("SELECT new UserEntity(u.id, u.currentQuota) FROM UserEntity u")
    List<UserEntity> findAllUsersQuota();

}
