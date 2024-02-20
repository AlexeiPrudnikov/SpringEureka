package ru.geekbrains;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.aspect.ExecTimerAspect;

@Configuration
@EnableConfigurationProperties(ExecTimerAspect.class)
//@ConditionalOnProperty(value = "http.logging.enabled", havingValue = "true")
public class TimerAutoConfiguration {
    @Bean
    ExecTimerAspect timerAspect() {
        return new ExecTimerAspect();
    }
}
