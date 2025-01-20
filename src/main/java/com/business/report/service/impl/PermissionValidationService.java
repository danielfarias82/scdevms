package com.business.report.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.business.report.repo.UserPermissionRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class PermissionValidationService {

    private final RestTemplate restTemplate;
    private final UserPermissionRepository userPermissionRepository;

    public PermissionValidationService(RestTemplate restTemplate, UserPermissionRepository userPermissionRepository) {
        this.restTemplate = restTemplate;
        this.userPermissionRepository = userPermissionRepository;
    }

    public boolean validateAccess(String userId, String token) {
        String url = "https://uat-us-api.experian.com/oauth2/v1/introspect";
        var response = restTemplate.postForObject(url, createRequestBody(token), Boolean.class);

        if (response != null && response) {
            return userPermissionRepository.existsByUserId(userId);
        }
        return false;
    }

    private Map<String, String> createRequestBody(String token) {
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        return body;
    }
}
