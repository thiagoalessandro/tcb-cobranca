package br.com.totvs.tcb.cobranca.fixture.dto.request;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.SolicitacaoCfopRequestDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class SolicitacaoCfopRequestDTOFixture extends FixtureAbstract {

    public static SolicitacaoCfopRequestDTO valido() {
        SolicitacaoCfopRequestDTO requestDTO = new SolicitacaoCfopRequestDTO();
        requestDTO.setPaymentIntegratorAccountId(faker.idNumber().valid());
        requestDTO.setTransactionDescription(faker.book().title());
        requestDTO.setCurrencyCode("USD");
        requestDTO.setAmount(faker.number().randomNumber());
        requestDTO.setRequestHeader(HeaderCfopRequestDTOFixture.valido());
        return requestDTO;
    }

}
