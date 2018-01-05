package com.pablo.acs.rfid.service.domain.export;

public class ExportRfidUuid {

    private final int id;
    private final String uuid;

    public ExportRfidUuid(final int id, final String uuid) {

        this.id = id;
        this.uuid = uuid;
    }

    int getUserId() {
        return id;
    }

    String getUUID() {
        return uuid;
    }
}
