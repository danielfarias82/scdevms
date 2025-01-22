package com.business.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeudaResponse {

    @JsonProperty("CodigoRetorno")
    private int codigoRetorno;

    @JsonProperty("Resumen")
    private Resumen resumen;

    @JsonProperty("Detalle")
    private List<Detalle> detalle;

    @Data
    public static class Resumen {

        @JsonProperty("ExisteDetalle")
        private String existeDetalle;

        @JsonProperty("AñoMesDeuda")
        private String añoMesDeuda;

        @JsonProperty("DeudaTotal")
        private String deudaTotal;

        @JsonProperty("NombreSuperIntendencia")
        private String nombreSuperIntendencia;
    }

    @Data
    public static class Detalle {

        @JsonProperty("AñoMesDeuda")
        private String añoMesDeuda;

        @JsonProperty("DeudaDirectaVig")
        private String deudaDirectaVig;

        @JsonProperty("DeudaxInversFinan")
        private String deudaxInversFinan;

        @JsonProperty("DeudaVencDirecta")
        private String deudaVencDirecta;

        @JsonProperty("DeudaxOperConPacto")
        private String deudaxOperConPacto;

        @JsonProperty("DeudaIndirectaVig")
        private String deudaIndirectaVig;

        @JsonProperty("DeudaIndirectaVenc")
        private String deudaIndirectaVenc;

        @JsonProperty("DeudaComercial")
        private String deudaComercial;

        @JsonProperty("DeudaxCredConsu")
        private String deudaxCredConsu;

        @JsonProperty("NumAcreexCredConsu")
        private String numAcreexCredConsu;

        @JsonProperty("DeudaxCredHipo")
        private String deudaxCredHipo;

        @JsonProperty("DeudaMorosa")
        private String deudaMorosa;

        @JsonProperty("DeudaCastigDirecta")
        private String deudaCastigDirecta;

        @JsonProperty("DeudaCastigIndirecta")
        private String deudaCastigIndirecta;

        @JsonProperty("MtoLineaCredDisp")
        private String mtoLineaCredDisp;

        @JsonProperty("DeudaComVigMEx")
        private String deudaComVigMEx;

        @JsonProperty("DeudaComVencMEx")
        private String deudaComVencMEx;
    }
}
