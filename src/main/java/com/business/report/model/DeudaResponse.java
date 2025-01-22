package com.business.report.model;

import lombok.Data;
import java.util.List;

@Data
public class DeudaResponse {
    private int codigoRetorno;
    private Resumen resumen;
    private List<Detalle> detalle;

    @Data
    public static class Resumen {
        private String existeDetalle;
        private String añoMesDeuda;
        private String deudaTotal;
        private String nombreSuperIntendencia;
    }

    @Data
    public static class Detalle {
        private String añoMesDeuda;
        private String deudaDirectaVig;
        private String deudaxInversFinan;
        private String deudaVencDirecta;
        private String deudaxOperConPacto;
        private String deudaIndirectaVig;
        private String deudaIndirectaVenc;
        private String deudaComercial;
        private String deudaxCredConsu;
        private String numAcreexCredConsu;
        private String deudaxCredHipo;
        private String deudaMorosa;
        private String deudaCastigDirecta;
        private String deudaCastigIndirecta;
        private String mtoLineaCredDisp;
        private String deudaComVigMEx;
        private String deudaComVencMEx;
    }
}
