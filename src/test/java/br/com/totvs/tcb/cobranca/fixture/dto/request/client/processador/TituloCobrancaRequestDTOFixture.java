package br.com.totvs.tcb.cobranca.fixture.dto.request.client.processador;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.TituloCobrancaRequestDTO;
import br.com.totvs.tcb.cobranca.domain.DominioEspecieTitulo;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

import java.math.BigDecimal;
import java.util.Date;

public class TituloCobrancaRequestDTOFixture extends FixtureAbstract {

    public static TituloCobrancaRequestDTO valido() {
        TituloCobrancaRequestDTO tituloCobrancaRequestDTO = new TituloCobrancaRequestDTO();
        tituloCobrancaRequestDTO.setControle(ControleRequestDTOFixture.valido());
        tituloCobrancaRequestDTO.setCedente(CedenteTituloRequestDTOFixture.valido());
        tituloCobrancaRequestDTO.setSacado(SacadoTituloRequestDTOFixture.valido());
        tituloCobrancaRequestDTO.setNumeroDocumentoCobranca(faker.number().digits(15));
        tituloCobrancaRequestDTO.setDataEmissao(new Date());
        tituloCobrancaRequestDTO.setDataVencimento(new Date());
        tituloCobrancaRequestDTO.setValorTitulo(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 9999)));
        tituloCobrancaRequestDTO.setEspecieTitulo(DominioEspecieTitulo.DUPLICATA_SERVICO);
        return tituloCobrancaRequestDTO;
    }

}
