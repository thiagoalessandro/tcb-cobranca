package br.com.totvs.tcb.cobranca.fixture.dto.request;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.TituloCobrancaRequestDTO;
import br.com.totvs.tcb.cobranca.domain.DominioEspecieTitulo;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;

import java.math.BigDecimal;
import java.util.Date;

public class CobrancaBoletoRequestDTOFixture extends FixtureAbstract {

    public static TituloCobrancaRequestDTO valido() {
        TituloCobrancaRequestDTO tituloCobrancaRequestDTO = new TituloCobrancaRequestDTO();
        tituloCobrancaRequestDTO.setCedente(CedenteBoletoRequestDTOFixture.valido());
        tituloCobrancaRequestDTO.setSacado(SacadoBoletoRequestDTOFixture.valido());
        tituloCobrancaRequestDTO.setNumeroDocumentoCobranca(faker.number().digits(15));
        tituloCobrancaRequestDTO.setDataEmissao(new Date());
        tituloCobrancaRequestDTO.setDataVencimento(new Date());
        tituloCobrancaRequestDTO.setValorTitulo(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 9999)));
        tituloCobrancaRequestDTO.setEspecieTitulo(DominioEspecieTitulo.DUPLICATA_SERVICO);
        return tituloCobrancaRequestDTO;
    }

}
