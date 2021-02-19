package br.com.totvs.tcb.cobranca.config;

import br.com.totvs.tcb.cobranca.encryptor.pgp.PgpEncryptor;
import br.com.totvs.tcb.cobranca.encryptor.pgp.PgpKeyManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PgpEncryptorConfig {

    @Bean
    public PgpKeyManager pgpKeyManager() {
        return new PgpKeyManager();
    }

    @Bean
    public PgpEncryptor pgpEncryptor() {
        return new PgpEncryptor(pgpKeyManager());
    }

}
