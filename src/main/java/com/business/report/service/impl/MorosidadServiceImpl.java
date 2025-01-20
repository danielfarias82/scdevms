package com.business.report.service.impl;

import com.business.report.model.ConsolidadoMorosidadRequest;
import com.business.report.model.ConsolidadoMorosidadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MorosidadServiceImpl {

    @Value("${experian.services.irut_consolidado_morosidad}")
    private String serviceUrl;

    private final RestTemplate restTemplate;
    private final TokenServiceImpl tokenService;

    public MorosidadServiceImpl(RestTemplate restTemplate, TokenServiceImpl tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public ConsolidadoMorosidadResponse consultarMorosidad(ConsolidadoMorosidadRequest request) {
        // Obtener el token de acceso
        String token = tokenService.getAccessToken();

        // Crear los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        // Crear la entidad de la solicitud
        HttpEntity<ConsolidadoMorosidadRequest> requestEntity = new HttpEntity<>(request, headers);

        // Realizar la solicitud al endpoint
        try {
            return restTemplate.postForObject(serviceUrl, requestEntity, ConsolidadoMorosidadResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la morosidad: " + e.getMessage(), e);
        }
    }
}
