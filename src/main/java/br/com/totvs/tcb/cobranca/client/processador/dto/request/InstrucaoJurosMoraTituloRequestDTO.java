package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioJurosMora;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InstrucaoJurosMoraTituloRequestDTO extends BaseInstrucaoTituloRequestDTO {

    private DominioJurosMora tipo;

}
