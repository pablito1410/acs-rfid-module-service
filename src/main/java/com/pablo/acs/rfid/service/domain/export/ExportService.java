package com.pablo.acs.rfid.service.domain.export;

import com.pablo.acs.rfid.service.domain.ports.RestClient;
import com.pablo.acs.rfid.service.domain.ports.SystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportService {


    private final RestClient restClientDefault;
    private final SystemProfile systemProfile;

    @Autowired
    public ExportService(final RestClient restClientDefault, final SystemProfile systemProfile) {
        this.restClientDefault = restClientDefault;
        this.systemProfile = systemProfile;
    }

    public void handle(final ExportRfidUuid command) {
        final AddUserIdentifyMethod addIdentifyMethodCommand =
                new AddUserIdentifyMethod(command.getUserId(), command.getUUID());
        restClientDefault.post(systemProfile.getExportUrl(), addIdentifyMethodCommand);
    }
}
