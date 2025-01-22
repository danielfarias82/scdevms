package com.business.report.service.impl;

import com.business.report.model.DeudaRequest;
import com.business.report.model.DeudaResponse;
import com.business.report.model.DestinoRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeudaService {

    @Value("${experian.services.iurl_deuda}")
    private String deudaServiceUrl;

    @Value("${experian.services.destino}")
    private String destinoServiceUrl;

    private final RestTemplate restTemplate;
    private final TokenServiceImpl tokenService;

    public DeudaService(RestTemplate restTemplate, TokenServiceImpl tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public void procesarDeuda(DeudaRequest request) {
        // Obtener el token de acceso
        String token = tokenService.getAccessToken();

        // Crear los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        // Crear la entidad de la solicitud
        HttpEntity<DeudaRequest> requestEntity = new HttpEntity<>(request, headers);

        try {
            // Obtener respuesta como String
            String responseString = restTemplate.postForObject(deudaServiceUrl, requestEntity, String.class);
            System.out.println("Respuesta de Experian: " + responseString);

            // Configurar ObjectMapper para ignorar campos desconocidos
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Mapear la respuesta al objeto DeudaResponse
            DeudaResponse deudaResponse = objectMapper.readValue(responseString, DeudaResponse.class);

            // Construir el request para el servicio de destino
            DestinoRequest destinoRequest = new DestinoRequest();
            destinoRequest.setSzData(responseString); // JSON de la respuesta original
            destinoRequest.setRutCliente(request.getRut());

            // Enviar la solicitud al servicio de destino
            HttpHeaders destinoHeaders = new HttpHeaders();
            destinoHeaders.set("Content-Type", "application/json");

            HttpEntity<DestinoRequest> destinoEntity = new HttpEntity<>(destinoRequest, destinoHeaders);

            ResponseEntity<String> destinoResponse = restTemplate.postForEntity(destinoServiceUrl, destinoEntity,
                    String.class);
            System.out.println("Respuesta del servicio de destino: " + destinoResponse.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la deuda: " + e.getMessage(), e);
        }
    }
}
