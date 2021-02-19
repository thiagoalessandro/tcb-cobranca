package br.com.totvs.tcb.cobranca.fixture.dto.request;

import br.com.totvs.tcb.cobranca.domain.DominioTipoInscricao;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.CedenteTituloRequestDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class CedenteBoletoRequestDTOFixture extends FixtureAbstract {

    public static CedenteTituloRequestDTO valido() {
        CedenteTituloRequestDTO cedenteTituloRequestDTO = new CedenteTituloRequestDTO();
        cedenteTituloRequestDTO.setNome(faker.name().fullName());
        cedenteTituloRequestDTO.setTipoInscricao(DominioTipoInscricao.CPF);
        cedenteTituloRequestDTO.setNumeroInscricao("37278145783");
        cedenteTituloRequestDTO.setCodigoConvenio(faker.number().digits(5));
        cedenteTituloRequestDTO.setNumeroAgencia(faker.number().randomDigitNotZero());
        cedenteTituloRequestDTO.setDigitoAgencia(faker.number().digits(1));
        cedenteTituloRequestDTO.setNumeroConta(faker.number().randomDigitNotZero());
        cedenteTituloRequestDTO.setDigitoConta(faker.number().digits(1));
        return cedenteTituloRequestDTO;
    }

}
