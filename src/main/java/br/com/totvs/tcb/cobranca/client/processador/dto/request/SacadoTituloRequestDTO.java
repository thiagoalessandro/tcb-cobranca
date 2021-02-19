package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioTipoInscricao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SacadoTituloRequestDTO implements Serializable {

    private String nome;
    private DominioTipoInscricao tipoInscricao;
    private String numeroInscricao;
    private String uf;
    private String cidade;
    private String cep;
    private String bairro;
    private String endereco;

}
