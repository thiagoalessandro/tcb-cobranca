package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class ControleRequestDTO implements Serializable {

    private String uuid;
    private Date dataHoraEvento;

}
