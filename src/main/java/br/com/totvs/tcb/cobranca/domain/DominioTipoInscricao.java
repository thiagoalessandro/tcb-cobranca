package br.com.totvs.tcb.cobranca.domain;

public enum DominioTipoInscricao {

    CPF(1),
    CNPJ(3);

    private final Integer value;

    DominioTipoInscricao(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
