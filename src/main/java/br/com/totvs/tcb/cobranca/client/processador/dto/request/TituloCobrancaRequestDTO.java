package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioEspecieTitulo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class TituloCobrancaRequestDTO implements Serializable {

    private CedenteTituloRequestDTO cedente;
    private SacadoTituloRequestDTO sacado;
    private String numeroDocumentoCobranca;
    private BigDecimal valorAbatimento;
    private Date dataEmissao;
    private Date dataVencimento;
    private BigDecimal valorTitulo;
    private DominioEspecieTitulo especieTitulo;
    private InstrucoesTituloRequestDTO instrucoes;

}
