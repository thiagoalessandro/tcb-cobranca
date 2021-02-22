package br.com.totvs.tcb.cobranca.fixture.dto.request.client.processador;

import java.util.Date;
import java.util.UUID;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.CedenteTituloRequestDTO;
import br.com.totvs.tcb.cobranca.client.processador.dto.request.ControleRequestDTO;
import br.com.totvs.tcb.cobranca.domain.DominioTipoInscricao;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class ControleRequestDTOFixture extends FixtureAbstract {

    public static ControleRequestDTO valido() {
        ControleRequestDTO controleRequestDTO = new ControleRequestDTO();
        controleRequestDTO.setUuid(UUID.randomUUID().toString());
        controleRequestDTO.setDataHoraEvento(new Date());
        return controleRequestDTO;
    }

}
