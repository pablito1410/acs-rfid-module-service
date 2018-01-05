package com.pablo.acs.rfid.service.domain.notification;

public interface NotificationSender {
    void inputCard();

    void takeOffCard();

    void processingCard();

    void identified(String uuid);
}
