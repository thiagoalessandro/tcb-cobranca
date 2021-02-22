package br.com.totvs.tcb.cobranca.client.processador.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class TituloCobrancaResponseDTO implements Serializable {

    private String codigoBarras;
    private String linhaDigitavel;
    private String nossoNumero;

}
