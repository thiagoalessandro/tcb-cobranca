package br.com.totvs.tcb.cobranca.repository;

import br.com.totvs.tcb.cobranca.domain.DominioTipoCertificado;
import br.com.totvs.tcb.cobranca.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    List<Certificado> findAllByTipoCertificado(DominioTipoCertificado tipoCertificado);

    List<Certificado> findAllByNomeArquivoOrigem(String nomeArquivoOrigem);

}
