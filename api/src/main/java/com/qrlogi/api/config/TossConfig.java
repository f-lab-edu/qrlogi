package com.qrlogi.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Configuration
public class TossConfig {

    @Value("${toss.secret-key}")
    private String secretKey;

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            String encodedKeyInBase64 = Base64.getEncoder().encodeToString(secretKey.getBytes());
            HttpHeaders headers = request.getHeaders();
            headers.setBasicAuth(encodedKeyInBase64);
            headers.setContentType(MediaType.APPLICATION_JSON);

            return execution.execute(request, body);

        });

        return restTemplate;
    }


}
