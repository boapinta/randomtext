package com.github.randomtext.history;

import com.github.randomtext.text.RandomTextService;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by alexey on 6/17/17.
 */
@EnableAsync
@Configuration
public class HistoryConfiguration implements AsyncConfigurer {

    @Bean
    public TextResponseProcessedEventListener processedEventListener(HistoryRepository repository) {
        return new TextResponseProcessedEventListener(repository);
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("ASYNC-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Primary
    @Bean
    public RandomTextService processedEventRandomTextService(RandomTextService delegate, ApplicationEventPublisher publisher) {
        return new ProcessedEventRandomTextService(delegate, publisher);
    }
}
