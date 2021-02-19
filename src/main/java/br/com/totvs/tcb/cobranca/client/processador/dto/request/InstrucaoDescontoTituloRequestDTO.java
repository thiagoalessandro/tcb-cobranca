package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioDesconto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InstrucaoDescontoTituloRequestDTO extends BaseInstrucaoTituloRequestDTO {

    private DominioDesconto tipo;

}
