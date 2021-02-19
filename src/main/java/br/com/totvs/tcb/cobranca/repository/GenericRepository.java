package br.com.totvs.tcb.cobranca.repository;

import br.com.totvs.tcb.cobranca.domain.DominioSituacaoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findBySituacao(DominioSituacaoRegistro situacao);

}
