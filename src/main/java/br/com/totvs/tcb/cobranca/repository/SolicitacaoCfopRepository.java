package br.com.totvs.tcb.cobranca.repository;

import br.com.totvs.tcb.cobranca.model.cfop.SolicitacaoCfop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoCfopRepository extends GenericRepository<SolicitacaoCfop, Long> {

}
