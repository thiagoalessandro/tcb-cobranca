package br.com.totvs.tcb.cobranca.fixture.dto.response;

import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

public class CobrancaBoletoResponseDTOFixture extends FixtureAbstract {

    public static TituloCobrancaResponseDTO valido() {
        TituloCobrancaResponseDTO tituloCobrancaResponseDTO = new TituloCobrancaResponseDTO();
        tituloCobrancaResponseDTO.setCodigoBarras(faker.number().digits(44));
        tituloCobrancaResponseDTO.setLinhaDigitavel(faker.number().digits(47));
        return tituloCobrancaResponseDTO;
    }

}
