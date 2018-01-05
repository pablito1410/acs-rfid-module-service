package com.pablo.acs.rfid.service.infrastructure.profile;

import com.pablo.acs.rfid.service.domain.ports.SystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class SystemProfileDefault implements SystemProfile {

    private AuthenticationServiceProfile authenticationServiceProfile;
    private NotificationProfile notificationProfile;

    @Autowired
    public SystemProfileDefault(final AuthenticationServiceProfile authenticationServiceProfile,
                                final NotificationProfile notificationProfile) {
        this.authenticationServiceProfile = authenticationServiceProfile;
        this.notificationProfile = notificationProfile;
    }

    @Override
    public String getExportUrl() {
        return authenticationServiceProfile.getExportUrl();
    }

    @Override
    public Collection<String> getNotificationEndpoints() {
        return notificationProfile.getEndpoints();
    }
}
