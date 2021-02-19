package br.com.totvs.tcb.cobranca;

import br.com.totvs.tcb.cobranca.domain.DominioTipoCertificado;
import br.com.totvs.tcb.cobranca.encryptor.pgp.KeyManagementException;
import br.com.totvs.tcb.cobranca.encryptor.pgp.PgpKeyManager;
import br.com.totvs.tcb.cobranca.model.Certificado;
import br.com.totvs.tcb.cobranca.service.CertificadoLocalService;
import br.com.totvs.tcb.cobranca.service.CertificadoService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;

@Log4j2
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final CertificadoService certificadoService;
    private final CertificadoLocalService certificadoLocalService;
    private final PgpKeyManager pgpKeyManager;
    private final Environment environment;
    private final StringEncryptor stringEncryptor;

    public AppStartupRunner(CertificadoService certificadoService,
                            CertificadoLocalService certificadoLocalService,
                            PgpKeyManager pgpKeyManager,
                            Environment environment, StringEncryptor stringEncryptor) {
        this.certificadoService = certificadoService;
        this.certificadoLocalService = certificadoLocalService;
        this.pgpKeyManager = pgpKeyManager;
        this.environment = environment;
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public void run(ApplicationArguments args) {
        incluirCertificadosLocalDB();
        carregarCertificados();
    }

    public void carregarCertificados() {
        try {
            log.info("Buscando certificados...");
            Iterable<Certificado> pgpPrivateKeys = certificadoService.findAllByTipoCertificado(DominioTipoCertificado.PGP_PRIVATE_KEY);
            Iterable<Certificado> pgpPublicKeys = certificadoService.findAllByTipoCertificado(DominioTipoCertificado.PGP_PUBLIC_KEY);
            Collection<Certificado> certificados = CollectionUtils.union(pgpPrivateKeys, pgpPublicKeys);
            addPgpKeyManager(certificados);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao carregar certificados", e);
        }
    }

    public void incluirCertificadosLocalDB() {
        try {
            if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
                certificadoLocalService.incluirCertificadosLocal();
            }
        } catch (Exception e) {
            log.error("Ocorreu um problema ao incluir certificados ambiente local", e);
        }
    }

    private void addPgpKeyManager(Collection<Certificado> certificados) {
        if (CollectionUtils.isNotEmpty(certificados))
            certificados.forEach(certificado -> {
                try {
                    log.info("Adicionando certificado {} -> {} ao PgpKeyManager", certificado.getTipoCertificado().name(), certificado.getNomeArquivoOrigem());
                    switch (certificado.getTipoCertificado()) {
                        case PGP_PRIVATE_KEY:
                            pgpKeyManager.addSecretKeys(new ByteArrayInputStream(certificado.getCertificado()), stringEncryptor.decrypt(certificado.getSenha()).toCharArray());
                            break;
                        case PGP_PUBLIC_KEY:
                            pgpKeyManager.addPublicKeys(new ByteArrayInputStream(certificado.getCertificado()));
                            break;
                    }
                } catch (KeyManagementException e) {
                    log.error("Ocorreu um erro ao carregar certificado {}", certificado.getId().toString());
                }
            });
    }
}
