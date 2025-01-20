package com.business.report.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExperianRequest {
    @NotNull
    private String queryType;
    @NotNull
    private String user;
    private String taxId;
    private String branchCode;
}