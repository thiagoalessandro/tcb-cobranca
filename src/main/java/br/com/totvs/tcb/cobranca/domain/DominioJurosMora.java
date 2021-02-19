package br.com.totvs.tcb.cobranca.domain;

public enum DominioJurosMora {

    VALOR_DIA(1),
    TAXA_MENSAL(2),
    ISENTO(3);

    private final Integer value;

    DominioJurosMora(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
