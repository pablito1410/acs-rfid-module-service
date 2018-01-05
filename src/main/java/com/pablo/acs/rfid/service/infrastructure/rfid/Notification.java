package com.pablo.acs.rfid.service.infrastructure.rfid;

public class Notification {

    private int notificationType;

    protected Notification() { }

    public Notification(final int notificationType) {
        this.notificationType = notificationType;
    }
}