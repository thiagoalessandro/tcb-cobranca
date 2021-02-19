package br.com.totvs.tcb.cobranca.fixture.dto.request;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.HeaderCfopRequestDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

import java.util.Date;
import java.util.UUID;

public class HeaderCfopRequestDTOFixture extends FixtureAbstract {

    public static HeaderCfopRequestDTO valido() {
        HeaderCfopRequestDTO requestDTO = new HeaderCfopRequestDTO();
        requestDTO.setProtocolVersion(ProtocolVersionCfopRequestDTOFixture.valido());
        requestDTO.setRequestId(UUID.randomUUID().toString());
        requestDTO.setRequestTimestamp(new Date().getTime());
        return requestDTO;
    }

}
