package com.pablo.acs.rfid.service.domain.rfid.exception;

public class EnrollmentException extends RuntimeException {
    public EnrollmentException(final Exception e) {
        super(e);
    }

    public EnrollmentException(final String message) {
        super(message);
    }
}
