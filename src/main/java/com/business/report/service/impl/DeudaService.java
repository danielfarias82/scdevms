package com.business.report.service.impl;

import com.business.report.model.DeudaRequest;
import com.business.report.model.DeudaResponse;
import com.business.report.model.DestinoRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.business.report.model.DeudaWrapperResponse;

@Service
public class DeudaService {

    @Value("${experian.services.iurl_deuda}")
    private String deudaServiceUrl;

    @Value("${experian.services.destino}")
    private String destinoServiceUrl;

    private final RestTemplate restTemplate;
    private final TokenServiceImpl tokenService;

    public DeudaService(@Qualifier("proxyRestTemplate") RestTemplate restTemplate, TokenServiceImpl tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public void procesarDeuda(DeudaRequest request) {
        // Obtener el token de acceso
        String token = tokenService.getAccessToken();

        // Crear los encabezados para la consulta a Experian
        HttpHeaders experianHeaders = new HttpHeaders();
        experianHeaders.set("Content-Type", "application/json");
        experianHeaders.set("Authorization", "Bearer " + token);

        // Crear la entidad de la solicitud
        HttpEntity<DeudaRequest> requestEntity = new HttpEntity<>(request, experianHeaders);

        try {
            // Consultar Experian y obtener respuesta como String
            String responseString = restTemplate.postForObject(deudaServiceUrl, requestEntity, String.class);
            System.out.println("Respuesta de Experian: " + responseString);

            // Configurar ObjectMapper para ignorar campos desconocidos
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Mapear la respuesta al objeto DeudaWrapperResponse
            DeudaWrapperResponse wrapperResponse = objectMapper.readValue(responseString, DeudaWrapperResponse.class);

            if (wrapperResponse == null || wrapperResponse.getData() == null) {
                throw new RuntimeException("La respuesta de Experian no contiene datos válidos.");
            }

            DeudaResponse deudaResponse = wrapperResponse.getData();

            // Validar si la respuesta tiene el resumen esperado
            if (deudaResponse.getResumen() == null) {
                throw new RuntimeException("La respuesta de Experian no contiene un resumen válido.");
            }

            // Verificar si hay detalle basado en "ExisteDetalle"
            String existeDetalle = deudaResponse.getResumen().getExisteDetalle();
            if ("N".equalsIgnoreCase(existeDetalle)) {
                System.out.println("No hay detalles en la respuesta de Experian.");
            } else if ("S".equalsIgnoreCase(existeDetalle)) {
                if (deudaResponse.getDetalle() == null || deudaResponse.getDetalle().isEmpty()) {
                    throw new RuntimeException("Se esperaba un detalle en la respuesta, pero no se encontró.");
                }
                System.out.println("Detalle encontrado: " + deudaResponse.getDetalle());
            } else {
                throw new RuntimeException("Valor inesperado para 'ExisteDetalle': " + existeDetalle);
            }

            // Construir el request para el servicio de destino
            DestinoRequest destinoRequest = new DestinoRequest();
            destinoRequest.setSzData(responseString); // JSON de la respuesta original
            destinoRequest.setRutCliente(request.getRut());
            destinoRequest.setCodAccion("A");
            destinoRequest.setCodCon("IR07");
            destinoRequest.setIntCant(0);
            destinoRequest.setSzModo("E");

            // Crear los encabezados para el servicio de destino
            HttpHeaders destinoHeaders = new HttpHeaders();
            destinoHeaders.set("Content-Type", "application/json");

            HttpEntity<DestinoRequest> destinoEntity = new HttpEntity<>(destinoRequest, destinoHeaders);

            // Enviar la solicitud al servicio de destino
            ResponseEntity<String> destinoResponse = restTemplate.postForEntity(destinoServiceUrl, destinoEntity,
                    String.class);
            System.out.println("Respuesta del servicio de destino: " + destinoResponse.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la deuda: " + e.getMessage(), e);
        }
    }
}
