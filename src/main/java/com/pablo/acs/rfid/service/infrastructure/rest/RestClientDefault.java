package com.pablo.acs.rfid.service.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.acs.rfid.service.domain.ports.Response;
import com.pablo.acs.rfid.service.domain.ports.RestClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientDefault implements RestClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RestClientDefault(final RestTemplate restTemplate, final ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public <R> Response<R> post(final String url, final Object body, final Class<R> response)  {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(body), headers);
            final ResponseEntity<R> responseEntity = restTemplate.postForEntity(url, entity, response);
            return new Response<R>() {
                @Override
                public int getStatusCode() {
                    return responseEntity.getStatusCodeValue();
                }

                @Override
                public R getBody() {
                    return responseEntity.getBody();
                }
            };
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // TODO cos z tym zrobic
        }
        return null;
    }

    @Override
    public Response<Void> post(final String url, final Object body) {
        return post(url, body, Void.class);
    }
}
