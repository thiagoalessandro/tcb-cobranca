package br.com.totvs.tcb.cobranca.service;

import br.com.totvs.tcb.cobranca.domain.DominioTipoCertificado;
import br.com.totvs.tcb.cobranca.model.Certificado;
import br.com.totvs.tcb.cobranca.utils.FileUtils;
import br.com.totvs.tcb.cobranca.utils.LogUtils;
import lombok.extern.log4j.Log4j2;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@Profile({"dev", "test"})
public class CertificadoLocalService {

    @Value("${pgp.local.passwordPrivateKey}")
    private String passwordPrivateKey;

    @Value("classpath:certificate/${pgp.local.certificate.tcb.privateKey}")
    private Resource certificatePrivateKeyFile;

    @Value("classpath:certificate/${pgp.local.certificate.google.publicKey}")
    private Resource certificatePublicKeyGoogleFile;

    private final CertificadoService certificadoService;

    private final StringEncryptor stringEncryptor;

    public CertificadoLocalService(CertificadoService certificadoService, StringEncryptor stringEncryptor) {
        this.certificadoService = certificadoService;
        this.stringEncryptor = stringEncryptor;
    }

    public void incluirCertificadosLocal() throws IOException {
        if (certificadoService.findAllByNomeArquivoOrigem(certificatePrivateKeyFile.getFilename()).isEmpty())
            certificadoService.save(getCertificado(certificatePrivateKeyFile, passwordPrivateKey, DominioTipoCertificado.PGP_PRIVATE_KEY));
        if (certificadoService.findAllByNomeArquivoOrigem(certificatePublicKeyGoogleFile.getFilename()).isEmpty())
            certificadoService.save(getCertificado(certificatePublicKeyGoogleFile, null, DominioTipoCertificado.PGP_PUBLIC_KEY));
    }

    private Certificado getCertificado(Resource resource, String password, DominioTipoCertificado tipoCertificado) throws IOException {
        Certificado certificado = new Certificado();
        certificado.setCertificado(FileUtils.resourceToBytes(resource));
        certificado.setCodigoTransacao(Long.valueOf(LogUtils.gerarIdentificacaoUnica()));
        certificado.setNomeArquivoOrigem(resource.getFilename());
        certificado.setSenha(stringEncryptor.encrypt(password));
        certificado.setTipoCertificado(tipoCertificado);
        certificado.setCodigoUsuarioAtualizacao("DEFAULT");
        return certificado;
    }

}
