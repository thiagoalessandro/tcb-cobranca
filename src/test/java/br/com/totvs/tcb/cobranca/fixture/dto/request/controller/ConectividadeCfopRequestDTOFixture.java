package br.com.totvs.tcb.cobranca.fixture.dto.request.controller;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.ConectividadeCfopRequestDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class ConectividadeCfopRequestDTOFixture extends FixtureAbstract {

    public static ConectividadeCfopRequestDTO valido() {
        ConectividadeCfopRequestDTO requestDTO = new ConectividadeCfopRequestDTO();
        requestDTO.setClientMessage("SUCCESS");
        requestDTO.setRequestHeader(HeaderCfopRequestDTOFixture.valido());
        return requestDTO;
    }

}
