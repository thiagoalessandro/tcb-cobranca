package br.com.totvs.tcb.cobranca.fixture.dto.request.client.processador;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.CobrancaCfopRequestDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;
import br.com.totvs.tcb.cobranca.fixture.dto.request.controller.HeaderCfopRequestDTOFixture;

public class CobrancaCfopRequestDTOFixture extends FixtureAbstract {

    public static CobrancaCfopRequestDTO valido() {
        CobrancaCfopRequestDTO requestDTO = new CobrancaCfopRequestDTO();
        requestDTO.setPaymentIntegratorAccountId(faker.idNumber().valid());
        requestDTO.setTransactionDescription(faker.book().title());
        requestDTO.setCurrencyCode("USD");
        requestDTO.setAmount(faker.number().randomNumber());
        requestDTO.setRequestHeader(HeaderCfopRequestDTOFixture.valido());
        return requestDTO;
    }

}
