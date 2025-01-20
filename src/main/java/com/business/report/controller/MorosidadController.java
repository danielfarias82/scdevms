package com.business.report.controller;

import com.business.report.model.ConsolidadoMorosidadResponse;
import com.business.report.model.ConsolidadoMorosidadRequest;
import com.business.report.service.impl.MorosidadServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/experian/morosidad")
public class MorosidadController {

    private final MorosidadServiceImpl morosidadService;

    public MorosidadController(MorosidadServiceImpl morosidadService) {
        this.morosidadService = morosidadService;
    }

    @PostMapping
    public ConsolidadoMorosidadResponse consultarMorosidad(@RequestBody ConsolidadoMorosidadRequest request) {
        return morosidadService.consultarMorosidad(request);
    }
}
