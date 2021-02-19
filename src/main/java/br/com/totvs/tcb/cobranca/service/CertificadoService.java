package br.com.totvs.tcb.cobranca.service;

import br.com.totvs.tcb.cobranca.domain.DominioTipoCertificado;
import br.com.totvs.tcb.cobranca.model.Certificado;
import br.com.totvs.tcb.cobranca.repository.CertificadoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class CertificadoService {

    private final CertificadoRepository repository;

    public CertificadoService(CertificadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Certificado> findAllByTipoCertificado(DominioTipoCertificado tipoCertificado) {
        return this.repository.findAllByTipoCertificado(tipoCertificado);
    }

    @Transactional
    public List<Certificado> findAllByNomeArquivoOrigem(String nomeArquivoOrigem) {
        return this.repository.findAllByNomeArquivoOrigem(nomeArquivoOrigem);
    }

    @Transactional
    public Certificado save(Certificado certificado) {
        return repository.save(certificado);
    }

    //TODO: Ao salvar ou atualizar um certificado será necessário atualizar o PgpKeyManager

}
