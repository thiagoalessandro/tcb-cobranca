package br.com.totvs.tcb.cobranca.domain;

public enum DominioEspecieTitulo {

    CHEQUE("01"),
    DUPLICATA_MERCANTIL("02"),
    DUPLICATA_MERCANTIL_INDICACAO("03"),
    DUPLICATA_SERVICO("04"),
    DUPLICATA_SERVICO_INDICACAO("05"),
    DUPLICATA_RURAL("06"),
    LETRA_CAMBIO("07"),
    NOTA_CREDITO_COMERCIAL("08"),
    NOTA_CREDITO_A_EXPORTACAO("09"),
    NOTA_CREDITO_INDUSTRIAL("10"),
    NOTA_CREDITO_RURAL("11"),
    NOTA_PROMISSORIA("12"),
    NOTA_PROMISSORIA_RURAL("13"),
    TRIPLICATA_MERCANTIL("14"),
    TRIPLICATA_SERVICO("15"),
    NOTA_SEGURO("16"),
    RECIBO("17"),
    FATURA("18"),
    NOTA_DEBITO("19"),
    APOLICE_SEGURO("20"),
    MENSALIDADE_ESCOLAR("21"),
    PARCELA_CONSORCIO("22"),
    NOTA_FISCAL("23"),
    DOCUMENTO_DIVIDA("24"),
    CEDULA_PRODUTO_RURAL("25"),
    WARRANT("26"),
    DIVIDA_ATIVA_ESTADO("27"),
    DIVIDA_ATIVA_MUNICIPIO("28"),
    DIVIDA_ATIVA_UNIAO("29"),
    ENCARGOS_CONDOMINIAIS("30"),
    CARTAO_CREDITO("31"),
    BOLETO_PROPOSTA("32"),
    OUTROS("99");

    private final String value;

    DominioEspecieTitulo(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
