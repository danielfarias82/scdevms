package com.business.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinoRequest {

    @JsonProperty("szData")
    private String szData;

    @JsonProperty("codAccion")
    private String codAccion = "A";

    @JsonProperty("rutCliente")
    private String rutCliente;

    @JsonProperty("codCon")
    private String codCon = "IR07";

    @JsonProperty("intCant")
    private int intCant = 0;

    @JsonProperty("szModo")
    private String szModo = "E";
}
