package com.pablo.acs.rfid.service.infrastructure.rfid;

public class IdentifiedNotification extends Notification {

    private final String uuid;

    public IdentifiedNotification(final int i, final String uuid) {
        super(i);
        this.uuid = uuid;
    }
}
