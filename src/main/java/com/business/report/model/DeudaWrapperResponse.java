package com.business.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeudaWrapperResponse {

    @JsonProperty("data")
    private DeudaResponse data;
}
