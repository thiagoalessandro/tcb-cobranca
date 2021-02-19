package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioTipoInscricao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CedenteTituloRequestDTO implements Serializable {

    private String nome;
    private DominioTipoInscricao tipoInscricao;
    private String numeroInscricao;
    private String codigoConvenio;
    private Integer numeroAgencia;
    private String digitoAgencia;
    private Integer numeroConta;
    private String digitoConta;

}
