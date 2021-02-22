package br.com.totvs.tcb.cobranca.domain;

public enum DominioMoedaTitulo {

    USD("02"),
    BRL("09"),
    EUR("14");

    private final String value;

    DominioMoedaTitulo(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
