package br.com.totvs.tcb.cobranca.fixture.model;

import br.com.totvs.tcb.cobranca.domain.DominioSituacaoRegistro;
import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;
import br.com.totvs.tcb.cobranca.model.cfop.SolicitacaoCfop;
import br.com.totvs.tcb.cobranca.utils.CfopUtils;

import java.util.Date;

public class SolicitacaoCfopFixture extends FixtureAbstract {

    public static SolicitacaoCfop valido() {
        SolicitacaoCfop solicitacaoCfop = new SolicitacaoCfop();
        solicitacaoCfop.setId(faker.number().randomNumber());
        solicitacaoCfop.setContaIntegradora(ContaIntegradoraCfopFixture.valido());
        solicitacaoCfop.setCodigoMoeda(faker.currency().code());
        solicitacaoCfop.setDescricaoTransacao(faker.book().title());
        solicitacaoCfop.setValorSolicitacao(CfopUtils.convertMicroAmount(faker.number().randomNumber()));
        solicitacaoCfop.setSituacao(DominioSituacaoRegistro.ATIVO);
        solicitacaoCfop.setCodigoUsuarioAtualizacao(faker.name().username());
        solicitacaoCfop.setDataHoraAtualizacao(new Date());
        return solicitacaoCfop;
    }

}
