package br.com.totvs.tcb.cobranca.fixture.dto.request.controller;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.ProtocolVersionCfopRequestDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class ProtocolVersionCfopRequestDTOFixture extends FixtureAbstract {
    public static ProtocolVersionCfopRequestDTO valido() {
        ProtocolVersionCfopRequestDTO requestDTO = new ProtocolVersionCfopRequestDTO();
        requestDTO.setMajor(1);
        requestDTO.setMinor(2);
        requestDTO.setRevision(3);
        return requestDTO;
    }
}
