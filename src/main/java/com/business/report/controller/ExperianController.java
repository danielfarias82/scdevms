package com.business.report.controller;

import com.business.report.model.ExperianRequest;
import com.business.report.model.ExperianResponse;
import com.business.report.service.impl.ExperianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/experian")
public class ExperianController {

    @Autowired
    private ExperianServiceImpl experianService;

    @PostMapping("/document-status")
    public ExperianResponse getDocumentStatus(@RequestBody ExperianRequest request) {
        return experianService.getDocumentStatus(request);
    }
}
