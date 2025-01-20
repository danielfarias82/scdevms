package com.business.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ConsolidadoMorosidadResponse {

    private List<Object> errors;
    private boolean success;
    private ResponseData data;

    @Data
    public static class ResponseData {
        @JsonProperty("CodigoRetorno")
        private String codigoRetorno;

        @JsonProperty("ExisteDetalle")
        private String existeDetalle;

        @JsonProperty("NumAcreedores")
        private String numAcreedores;

        @JsonProperty("TotalDocImpago")
        private String totalDocImpago;

        @JsonProperty("Detalles")
        private List<Detalle> detalles;

        @Data
        public static class Detalle {
            @JsonProperty("TipoDeDoc")
            private String tipoDeDoc;

            @JsonProperty("FecDeVenc")
            private String fecDeVenc;

            @JsonProperty("NombreAportante")
            private String nombreAportante;

            @JsonProperty("TipoDeMoneda")
            private String tipoDeMoneda;

            @JsonProperty("Monto")
            private String monto;

            @JsonProperty("RutDeudorDirecto")
            private String rutDeudorDirecto;

            @JsonProperty("Direccion")
            private String direccion;

            @JsonProperty("Comuna")
            private String comuna;
        }
    }
}
