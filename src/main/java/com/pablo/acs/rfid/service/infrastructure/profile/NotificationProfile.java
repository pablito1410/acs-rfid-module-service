package com.pablo.acs.rfid.service.infrastructure.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "notifications.endpoints")
public class NotificationProfile {

    public Collection<String> getEndpoints() {
        final Collection<String> endpoints = new ArrayList<>();
        endpoints.add("http://localhost:5000/");
        return endpoints;
    }
}
