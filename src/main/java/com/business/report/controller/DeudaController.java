package com.business.report.controller;

import com.business.report.model.DeudaRequest;
import com.business.report.model.DeudaResponse;
import com.business.report.service.impl.DeudaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deuda")
public class DeudaController {

    private final DeudaService deudaService;

    public DeudaController(DeudaService deudaService) {
        this.deudaService = deudaService;
    }

    @PostMapping
    public DeudaResponse obtenerDeuda(@RequestBody DeudaRequest request) {
        return deudaService.obtenerDeuda(request);
    }
}
