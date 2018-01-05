package com.pablo.acs.rfid.service.domain.ports;

public interface Response <T> {

    int getStatusCode();

    T getBody();
}
