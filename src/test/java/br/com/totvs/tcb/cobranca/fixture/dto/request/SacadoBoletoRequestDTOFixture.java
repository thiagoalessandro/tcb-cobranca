package br.com.totvs.tcb.cobranca.fixture.dto.request;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.SacadoTituloRequestDTO;
import br.com.totvs.tcb.cobranca.domain.DominioTipoInscricao;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class SacadoBoletoRequestDTOFixture extends FixtureAbstract {

    public static SacadoTituloRequestDTO valido() {
        SacadoTituloRequestDTO sacadoTituloRequestDTO = new SacadoTituloRequestDTO();
        sacadoTituloRequestDTO.setNome(faker.name().fullName());
        sacadoTituloRequestDTO.setTipoInscricao(DominioTipoInscricao.CPF);
        sacadoTituloRequestDTO.setNumeroInscricao("37278145783");
        sacadoTituloRequestDTO.setUf(faker.address().state());
        sacadoTituloRequestDTO.setCidade(faker.address().cityName());
        sacadoTituloRequestDTO.setCep(faker.address().zipCode());
        sacadoTituloRequestDTO.setBairro(faker.address().firstName());
        sacadoTituloRequestDTO.setEndereco(faker.address().streetName());
        return sacadoTituloRequestDTO;
    }

}
