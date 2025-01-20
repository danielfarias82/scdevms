package com.business.report.controller;

import com.business.report.service.impl.PermissionValidationService;
import com.business.report.model.ExperianRequest;
import com.business.report.model.ExperianResponse;
import com.business.report.service.ExperianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/experian")
public class ExperianController {

    @Autowired
    private ExperianService experianService;

    @Autowired
    private PermissionValidationService permissionValidationService;

    @PostMapping("/fetch")
    public ResponseEntity<?> fetchData(@RequestHeader("Authorization") String authorization,
            @RequestBody ExperianRequest request) {
        String token = authorization.replace("Bearer ", "");

        if (!permissionValidationService.validateAccess(request.getUser(), token)) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        ExperianResponse response = experianService.fetchDataFromExperian(request);
        return ResponseEntity.ok(response);
    }
}
