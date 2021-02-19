package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class BaseInstrucaoTituloRequestDTO implements Serializable {

    private Date data;
    private BigDecimal valor;

}
