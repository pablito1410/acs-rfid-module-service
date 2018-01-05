package com.pablo.acs.rfid.service.infrastructure.rfid;

import com.pablo.acs.rfid.service.domain.notification.NotificationSender;
import com.pablo.acs.rfid.service.domain.ports.RestClient;
import com.pablo.acs.rfid.service.domain.ports.SystemProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthClientNotifier implements NotificationSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClientNotifier.class);
    private final RestClient restClient;
    private final SystemProfile systemProfile;

    @Autowired
    public AuthClientNotifier(final RestClient restClient, final SystemProfile systemProfile) {
        this.restClient = Objects.requireNonNull(restClient);
        this.systemProfile = Objects.requireNonNull(systemProfile);
    }

    private void notify(final Notification notification) {
        systemProfile.getNotificationEndpoints().forEach(endpoint -> restClient.post(endpoint, notification));
        LOGGER.info("Notification was send");
    }

    @Override
    public void inputCard() {
        final Notification notification = NotificationFactory.inputCard();
        notify(notification);
    }

    @Override
    public void takeOffCard() {
        final Notification notification = NotificationFactory.takeOffCard();
        notify(notification);
    }

    @Override
    public void processingCard() {
        final ProcessingCardNotification notification = NotificationFactory.processingCard();
        notify(notification);
    }

    @Override
    public void identified(final String uuid) {
        final IdentifiedNotification notification = NotificationFactory.identified(uuid);
        notify(notification);
    }
}
