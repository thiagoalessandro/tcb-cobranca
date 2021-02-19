package br.com.totvs.tcb.cobranca.repository;

import br.com.totvs.tcb.cobranca.model.LogTransacional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTransacionalRepository extends JpaRepository<LogTransacional, Long>, JpaSpecificationExecutor<LogTransacional> {

    Page<LogTransacional> findByRequestId(String requestId, Pageable pageable);


}
