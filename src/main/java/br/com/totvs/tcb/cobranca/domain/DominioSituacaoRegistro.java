package br.com.totvs.tcb.cobranca.domain;

public enum DominioSituacaoRegistro implements IBasicDominio {

    ATIVO("A"),
    BLOQUEADO("B"),
    INATIVO("I"),
    EXCLUIDO("E");

    private String description;

    DominioSituacaoRegistro(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static DominioSituacaoRegistro convertStringToEnum(String situacao) {
        switch (situacao) {
            case "A":
                return DominioSituacaoRegistro.ATIVO;
            case "B":
                return DominioSituacaoRegistro.BLOQUEADO;
            case "I":
                return DominioSituacaoRegistro.INATIVO;
            case "E":
                return DominioSituacaoRegistro.EXCLUIDO;
            default:
                return DominioSituacaoRegistro.ATIVO;
        }
    }


}
