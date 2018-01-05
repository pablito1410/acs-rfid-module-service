package com.pablo.acs.rfid.service.infrastructure.config;

import com.pablo.rc522.RC522;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RfidServiceConfig {

    @Bean
    public RC522 module() {
        return new RC522();
    }
}
