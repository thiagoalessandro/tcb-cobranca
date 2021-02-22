package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioEspecieTitulo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class TituloCobrancaRequestDTO extends AbstractRequestDTO {

    private CedenteTituloRequestDTO cedente;
    private SacadoTituloRequestDTO sacado;
    private String numeroDocumentoCobranca;
    private BigDecimal valorAbatimento;
    private Date dataEmissao;
    private Date dataVencimento;
    private BigDecimal valorTitulo;
    private String codigoMoeda;
    private DominioEspecieTitulo especieTitulo;
    private InstrucoesTituloRequestDTO instrucoes;

}
