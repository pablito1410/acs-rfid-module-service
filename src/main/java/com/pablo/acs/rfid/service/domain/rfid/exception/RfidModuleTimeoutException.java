package com.pablo.acs.rfid.service.domain.rfid.exception;

public class RfidModuleTimeoutException extends RuntimeException {
    public RfidModuleTimeoutException(final String message) {
        super(message);
    }
}
