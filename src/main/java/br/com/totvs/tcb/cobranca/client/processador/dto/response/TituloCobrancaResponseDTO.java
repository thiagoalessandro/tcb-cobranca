package br.com.totvs.tcb.cobranca.client.processador.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TituloCobrancaResponseDTO implements Serializable {

    private String codigoBarras;
    private String linhaDigitavel;

}
