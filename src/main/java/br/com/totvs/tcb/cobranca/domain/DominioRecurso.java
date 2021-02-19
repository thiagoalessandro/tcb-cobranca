package br.com.totvs.tcb.cobranca.domain;

public enum DominioRecurso implements IBasicDominio {

    CONECTIVIDADE("Teste de conectividade"),
    SOLICITACAO("Geração de numero de referência"),
    LOG_TRANSACIONAL("Logs Transacionais");

    private final String description;

    DominioRecurso(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
