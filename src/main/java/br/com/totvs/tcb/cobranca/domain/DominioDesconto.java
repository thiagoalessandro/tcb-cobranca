package br.com.totvs.tcb.cobranca.domain;

public enum DominioDesconto {

    VALOR_FIXO_DATA(1),
    PERCENTUA_DATA(2),
    VALOR_ANTECIPACAO_DIA_CORRIDO(3),
    VALOR_ANTECIPACAO_DIA_UTIL(4),
    PERCENTUA_VALOR_NOMINAL_DIA_CORRIDO(5),
    PERCENTUA_VALOR_NOMINAL_DIA_UTIL(6),
    CANCELAMENTO_DESCONTO(7);

    private final Integer value;

    DominioDesconto(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
