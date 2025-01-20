package com.business.report.service.impl;

import com.business.report.model.ExperianRequest;
import com.business.report.model.ExperianResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper; // Importación de ObjectMapper

@Service
public class ExperianServiceImpl {

    @Value("${experian.services.url}")
    private String serviceUrl;

    private final RestTemplate restTemplate;
    private final TokenServiceImpl tokenService;

    public ExperianServiceImpl(RestTemplate restTemplate, TokenServiceImpl tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public ExperianResponse getDocumentStatus(ExperianRequest request) {
        // Obtener el token de acceso
        String token = tokenService.getAccessToken();

        // Crear los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        // Crear la entidad de la solicitud
        HttpEntity<ExperianRequest> requestEntity = new HttpEntity<>(request, headers);

        // Enviar la solicitud al endpoint
        try {
            // Obtener la respuesta como String
            String responseString = restTemplate.postForObject(serviceUrl, requestEntity, String.class);
            System.out.println("Respuesta de la API de Experian: " + responseString);

            // Mapear la respuesta al objeto ExperianResponse
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseString, ExperianResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar el estado del documento: " + e.getMessage(), e);
        }
    }
}
