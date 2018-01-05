package com.pablo.acs.rfid.service.domain.ports;

import java.util.Collection;

public interface SystemProfile {

    String getExportUrl();

    Collection<String> getNotificationEndpoints();
}
