package br.com.totvs.tcb.cobranca.client.processador.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class InstrucoesTituloRequestDTO implements Serializable {

    private List<InstrucaoDescontoTituloRequestDTO> descontos;
    private List<InstrucaoJurosMoraTituloRequestDTO> jurosMora;
    private List<InstrucaoMultaTituloRequestDTO> multas;

}
