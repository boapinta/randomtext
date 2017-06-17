package com.github.randomtext.text;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.Executor;

/**
 * Created by alexey on 6/15/17.
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(EndpointSettings.class)
public class TextConfiguration implements AsyncConfigurer {

    @Autowired
    private EndpointSettings settings;

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public APIService apiService() {
        return new Retrofit.Builder()
                .baseUrl(settings.getBaseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
                .create(APIService.class);
    }

    @Bean
    public RandomTextService randomTextService() {
        return new DefaultRandomTextService(apiService());
    }

    @Bean
    public RandomTextService stopWatchRandomTextService() {
        return new StopWatchRandomTextService(randomTextService());
    }

    @Primary
    @Bean
    public RandomTextService processedEventRandomTextService(ApplicationEventPublisher publisher) {
        return new ProcessedEventRandomTextService(stopWatchRandomTextService(), publisher);
    }

    @Bean
    public TextResponseProcessedEventListener processedEventListener() {
        return new TextResponseProcessedEventListener();
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
}
