package com.pablo.acs.rfid.service.domain.ports;

public interface RestClient {

    <R> Response<R> post(String url, Object body, Class<R> response);

    Response<Void> post(String url, Object body);
}
