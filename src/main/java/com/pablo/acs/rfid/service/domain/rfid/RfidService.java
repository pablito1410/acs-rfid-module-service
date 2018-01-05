package com.pablo.acs.rfid.service.domain.rfid;

import com.pablo.acs.rfid.service.domain.export.ExportRfidUuid;
import com.pablo.acs.rfid.service.domain.export.ExportService;
import com.pablo.acs.rfid.service.domain.notification.NotificationSender;
import com.pablo.acs.rfid.service.domain.rfid.exception.EnrollmentException;
import com.pablo.acs.rfid.service.domain.rfid.exception.RfidModuleTimeoutException;
import com.pablo.rc522.RC522;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RfidService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RfidService.class);
    private final NotificationSender notificationSender;
    private static final Object LOCK = new Object();
    private final ExportService exportService;
    private final RC522 module;

    @Autowired
    public RfidService(final NotificationSender notificationSender, final ExportService exportService,
                       final RC522 module) {
        this.notificationSender = notificationSender;
        this.exportService = exportService;
        this.module = module;
    }


    public void handle(final EnrollCommand command) {
        synchronized (LOCK) {
            try {
                final int id = command.getUserId();

                LOGGER.info("Enrollment process start. Id=" + command.getUserId());

                notificationSender.inputCard();
                waitForFinger();
                final String uuid = module.readUUID();
                if (uuid == null) {
                    throw new EnrollmentException("Cannot read uuid");
                }
                LOGGER.info("Read rfid uuid process finished.");

                notificationSender.takeOffCard();

                exportService.handle(new ExportRfidUuid(id, uuid));
            } catch (final Exception e) {
                LOGGER.warn("Unexpected error");
                throw new EnrollmentException(e);
            }
        }
    }

    private void waitForFinger() {
        for (int i = 0; i < 30; i++) {
            try {
                if (module.readUUID() != null) {
                    return;
                }
                Thread.sleep(100);
            } catch (final InterruptedException e) {

            }
        }
        throw new RfidModuleTimeoutException("Timeout!");
    }

    public boolean isDevicePressed() {
        synchronized (LOCK) {
            return module.readUUID() != null;
        }
    }

    public void onDevicePressed() {
        synchronized (LOCK) {
            notifyProcessingCard();

            final String uuid = module.readUUID();
            if (uuid == null) {
                LOGGER.info("Identification failed");
            } else {
                identified(uuid);
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void identified(final String uuid) {
        LOGGER.info("Identified. uuid={}", uuid);
        notificationSender.identified(uuid);
    }

    private void notifyProcessingCard() {
        LOGGER.info("Processing rfid device...");
        notificationSender.processingCard();
    }
}
