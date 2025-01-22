package com.business.report.service.impl;

import com.business.report.model.DeudaRequest;
import com.business.report.model.DeudaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeudaService {

    @Value("${experian.services.irut_consolidado_morosidad}")
    private String experianUrl;

    private final RestTemplate restTemplate;

    public DeudaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DeudaResponse obtenerDeuda(DeudaRequest request) {
        // Enviar la solicitud POST a Experian
        return restTemplate.postForObject(experianUrl, request, DeudaResponse.class);
    }
}
