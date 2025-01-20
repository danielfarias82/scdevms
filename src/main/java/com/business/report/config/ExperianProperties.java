package com.business.report.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "experian")
public class ExperianProperties {

    private OAuth2 oauth2;
    private Service service;

    @Data
    public static class OAuth2 {
        private String url;
        private String clientId;
        private String clientSecret;
        private String username;
        private String password;
    }

    @Data
    public static class Service {
        private String url;
    }
}
