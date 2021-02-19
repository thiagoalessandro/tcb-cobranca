package br.com.totvs.tcb.cobranca.repository;

import br.com.totvs.tcb.cobranca.model.cfop.ContaIntegradoraCfop;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaIntegradoraCfopRepository extends GenericRepository<ContaIntegradoraCfop, Long> {

    Optional<ContaIntegradoraCfop> findByContaIntegradora(String contaIntegradora);

}
