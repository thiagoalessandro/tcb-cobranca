package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.ConectividadeCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.ConectividadeCfopResponseDTO;
import br.com.totvs.tcb.cobranca.encryptor.pgp.PgpEncryptor;
import br.com.totvs.tcb.cobranca.fixture.dto.request.controller.ConectividadeCfopRequestDTOFixture;
import br.com.totvs.tcb.cobranca.repository.ContaIntegradoraCfopRepository;
import br.com.totvs.tcb.cobranca.service.LogTransacionalService;
import br.com.totvs.tcb.cobranca.utils.ModelMapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConectividadeCfopService.class, ModelMapperUtils.class, PgpEncryptor.class})
public class ConectividadeCfopServiceTest {

    @Autowired
    private ConectividadeCfopService conectividadeCfopService;

    @MockBean
    private PgpEncryptor pgpEncryptor;

    @MockBean
    private LogTransacionalService logTransacionalService;

    @Test
    public void testeConectividadeSucesso() {
        ConectividadeCfopRequestDTO requestDTO = ConectividadeCfopRequestDTOFixture.valido();
        ConectividadeCfopResponseDTO responseDTO = conectividadeCfopService.processor(requestDTO);
        assertNotNull(responseDTO);
        assertEquals(requestDTO.getClientMessage(), "SUCCESS");
    }

}
