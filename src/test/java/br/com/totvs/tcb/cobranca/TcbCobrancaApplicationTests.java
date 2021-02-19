package br.com.totvs.tcb.cobranca;

import br.com.totvs.tcb.cobranca.fixture.FixtureAbstract;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class TcbCobrancaApplicationTests extends FixtureAbstract {

    @Test
    public void contextLoads() {
        log.info("Contexto iniciado");
    }

}
