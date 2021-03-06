package com.github.randomtext.text;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by alexey on 6/15/17.
 */
@Configuration
@EnableConfigurationProperties(EndpointSettings.class)
public class TextConfiguration {

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
    public RandomTextService stopWatchRandomTextService() {
        return new StopWatchRandomTextService(new DefaultRandomTextService(apiService()));
    }

}
