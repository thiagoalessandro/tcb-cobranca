package br.com.totvs.tcb.cobranca.fixture.model;

import br.com.totvs.tcb.cobranca.domain.DominioSituacaoRegistro;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;
import br.com.totvs.tcb.cobranca.model.cfop.CobrancaCfop;
import br.com.totvs.tcb.cobranca.utils.CfopUtils;

import java.util.Date;

public class CobrancaCfopFixture extends FixtureAbstract {

    public static CobrancaCfop valido() {
        CobrancaCfop cobrancaCfop = new CobrancaCfop();
        cobrancaCfop.setId(faker.number().randomNumber());
        cobrancaCfop.setContaIntegradora(ContaIntegradoraCfopFixture.valido());
        cobrancaCfop.setCodigoMoeda(faker.currency().code());
        cobrancaCfop.setDescricaoTransacao(faker.book().title());
        cobrancaCfop.setValorCobranca(CfopUtils.convertMicroAmount(faker.number().randomNumber()));
        cobrancaCfop.setSituacao(DominioSituacaoRegistro.ATIVO);
        cobrancaCfop.setCodigoUsuarioAtualizacao(faker.name().username());
        cobrancaCfop.setDataHoraAtualizacao(new Date());
        return cobrancaCfop;
    }

}
