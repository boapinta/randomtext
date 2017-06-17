package com.github.randomtext.text;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by alexey on 6/15/17.
 */
@Validated
@ConfigurationProperties("endpoint")
public class EndpointSettings {

    @NotNull
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
