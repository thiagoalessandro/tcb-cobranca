package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AbstractRequestDTO implements Serializable {

    private ControleRequestDTO controle;

}
