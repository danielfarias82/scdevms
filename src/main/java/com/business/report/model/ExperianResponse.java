package com.business.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ExperianResponse {
    private List<Object> errors;
    private boolean success;
    private ResponseData data;

    @Data
    public static class ResponseData {
        @JsonProperty("CodigoRetorno")
        private String codigoRetorno;

        @JsonProperty("ExisteDetalle")
        private String existeDetalle;

        @JsonProperty("CedulaVigente")
        private String cedulaVigente;

        @JsonProperty("NumeroRegistros")
        private String numeroRegistros;

        @JsonProperty("Detalles")
        private List<Detalle> detalles;

        @Data
        public static class Detalle {
            @JsonProperty("RutConsultado")
            private String rutConsultado;

            @JsonProperty("DigitoVerificador")
            private String digitoVerificador;

            @JsonProperty("TipoDocumento")
            private String tipoDocumento;

            @JsonProperty("NumeroSerie")
            private String numeroSerie;

            @JsonProperty("Razon")
            private String razon;

            @JsonProperty("Fecha")
            private String fecha;

            @JsonProperty("Fuente")
            private String fuente;
        }
    }
}
