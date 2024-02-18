package io.group.ratelimiter.unit.infra.adapter.provider;

import io.group.ratelimiter.infra.adapter.provider.UserRepositoryProviderTimeAdapter;
import io.group.ratelimiter.infra.adapter.repository.UserRepositoryElasticAdapter;
import io.group.ratelimiter.infra.adapter.repository.UserRepositoryMysqlAdapter;
import io.group.ratelimiter.infra.exception.RepositoryUnavailableException;
import io.group.ratelimiter.infra.model.TimeSchedule;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

class UserRepositoryProviderTimeAdapterUnitTest extends AbstractUnitTest {

    @Mock
    private UserRepositoryMysqlAdapter userMysqlRepository;

    @Mock
    private UserRepositoryElasticAdapter userElasticRepository;

    @Mock
    private TimeSchedule mysqlTimeSchedule;

    @Mock
    private TimeSchedule elasticTimeSchedule;

    private UserRepositoryProviderTimeAdapter userRepositoryProviderTimeAdapter;

    @BeforeEach
    void setUp() {
        userRepositoryProviderTimeAdapter = new UserRepositoryProviderTimeAdapter(userMysqlRepository, userElasticRepository, mysqlTimeSchedule, elasticTimeSchedule);
    }

    @Test
    void getRepository_shouldReturnMysqlRepository_whenTimeIsInMysqlSchedule() {
        doReturn(true).when(mysqlTimeSchedule).isTimeInSchedule(any());
        var repository = userRepositoryProviderTimeAdapter.getRepository();
        assertEquals(userMysqlRepository, repository);
    }

    @Test
    void getRepository_shouldReturnElasticRepository_whenTimeIsInElasticSchedule() {
        doReturn(false).when(mysqlTimeSchedule).isTimeInSchedule(any());
        doReturn(true).when(elasticTimeSchedule).isTimeInSchedule(any());
        var repository = userRepositoryProviderTimeAdapter.getRepository();
        assertEquals(userElasticRepository, repository);
    }

    @Test
    void getRepository_shouldThrowRepositoryUnavailableException_whenTimeIsNotInAnySchedule() {
        doReturn(false).when(mysqlTimeSchedule).isTimeInSchedule(any());
        doReturn(false).when(elasticTimeSchedule).isTimeInSchedule(any());
        assertThrows(RepositoryUnavailableException.class, () -> userRepositoryProviderTimeAdapter.getRepository());
    }

}