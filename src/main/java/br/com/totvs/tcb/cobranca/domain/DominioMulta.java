package br.com.totvs.tcb.cobranca.domain;

public enum DominioMulta {

    VALOR_FIXO(1),
    PERCENTUA(2);

    private final Integer value;

    DominioMulta(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
