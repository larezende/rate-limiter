package io.group.ratelimiter.infra.configuration;

import io.group.ratelimiter.infra.model.TimeSchedule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeScheduleConfiguration {

    @Bean("mysqlTimeSchedule")
    public TimeSchedule mysqlTimeSchedule(@Value("${schedule.mysql.start}") String start, @Value("${schedule.mysql.end}") String end) {
        return new TimeSchedule(start, end);
    }

    @Bean("elasticTimeSchedule")
    public TimeSchedule elasticTimeSchedule(@Value("${schedule.elastic.start}") String start, @Value("${schedule.elastic.end}") String end) {
        return new TimeSchedule(start, end);
    }

}
