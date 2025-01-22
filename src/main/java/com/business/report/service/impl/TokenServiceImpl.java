package com.business.report.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TokenServiceImpl {

    @Value("${experian.oauth2.url}")
    private String tokenUrl;

    @Value("${experian.oauth2.client-id}")
    private String clientId;

    @Value("${experian.oauth2.client-secret}")
    private String clientSecret;

    @Value("${experian.oauth2.username}")
    private String username;

    @Value("${experian.oauth2.password}")
    private String password;

    private final RestTemplate restTemplate;

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {
        // Configurar los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("client_id", clientId);
        headers.set("client_secret", clientSecret);

        // Configurar el cuerpo con solo username y password
        Map<String, String> body = Map.of(
                "username", username,
                "password", password);

        // Crear la entidad de la solicitud
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        try {
            // Realizar la solicitud al endpoint del token
            Map<String, Object> response = restTemplate.postForObject(tokenUrl, requestEntity, Map.class);
            System.out.println("Respuesta del token: " + response);

            // Verificar la respuesta
            if (response != null && response.containsKey("access_token")) {
                return (String) response.get("access_token");
            } else {
                throw new RuntimeException("No se recibió el token de acceso en la respuesta.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el token: " + e.getMessage(), e);
        }
    }
}
