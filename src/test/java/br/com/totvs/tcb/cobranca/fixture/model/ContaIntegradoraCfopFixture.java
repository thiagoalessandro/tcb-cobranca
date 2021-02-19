package br.com.totvs.tcb.cobranca.fixture.model;

import java.util.Date;

import br.com.totvs.tcb.cobranca.domain.DominioSituacaoRegistro;

import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;
import br.com.totvs.tcb.cobranca.model.cfop.ContaIntegradoraCfop;

public class ContaIntegradoraCfopFixture extends FixtureAbstract {

    public static ContaIntegradoraCfop valido() {
        ContaIntegradoraCfop contaIntegradoraCfop = new ContaIntegradoraCfop();
        contaIntegradoraCfop.setId(faker.number().randomNumber());
        contaIntegradoraCfop.setContaIntegradora(faker.idNumber().valid());
        contaIntegradoraCfop.setDataHoraCadastro(new Date());
        contaIntegradoraCfop.setSituacao(DominioSituacaoRegistro.ATIVO);
        contaIntegradoraCfop.setCodigoUsuarioAtualizacao(faker.name().username());
        contaIntegradoraCfop.setDataHoraAtualizacao(new Date());
        return contaIntegradoraCfop;
    }

}
