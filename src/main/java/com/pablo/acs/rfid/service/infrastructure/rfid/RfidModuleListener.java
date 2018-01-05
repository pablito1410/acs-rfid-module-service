package com.pablo.acs.rfid.service.infrastructure.rfid;

import com.pablo.acs.rfid.service.domain.rfid.RfidService;
import com.pablo.rc522.exception.CommunicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RfidModuleListener {

    private static final Logger log = LoggerFactory.getLogger(RfidModuleListener.class);
    private final RfidService rfidService;


    @Autowired
    public RfidModuleListener(final RfidService rfidService) {
        this.rfidService = rfidService;
    }

    @Scheduled(fixedDelay = 200)
    public void listen() {
        log.debug("Waiting for rfid device...");
        try {
            if (rfidService.isDevicePressed()) {
                rfidService.onDevicePressed();
            }
        } catch (CommunicationException e) {
            log.warn("RC522 exception: " + e.getMessage());
        }
    }
}
