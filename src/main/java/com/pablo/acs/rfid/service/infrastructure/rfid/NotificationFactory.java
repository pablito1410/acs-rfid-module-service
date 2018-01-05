package com.pablo.acs.rfid.service.infrastructure.rfid;

public class NotificationFactory {
    static Notification inputCard() {
        return new InputCardNotification(5);
    }

    static Notification takeOffCard() {
        return new TakeOffCardNoification(6);
    }

    static ProcessingCardNotification processingCard() {
        return new ProcessingCardNotification(7);
    }

    static IdentifiedNotification identified(final String uuid) {
        return new IdentifiedNotification(8, uuid);
    }
}
