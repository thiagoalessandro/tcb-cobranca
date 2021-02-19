package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.TcbCobrancaApplicationTests;
import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.ConectividadeCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.ConectividadeCfopResponseDTO;
import br.com.totvs.tcb.cobranca.fixture.dto.request.ConectividadeCfopRequestDTOFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class ConectividadeCfopServiceTest extends TcbCobrancaApplicationTests {

    @Autowired
    private ConectividadeCfopService conectividadeCfopService;

    @Test
    public void testeConectividadeSucesso() {
        ConectividadeCfopRequestDTO requestDTO = ConectividadeCfopRequestDTOFixture.valido();
        ConectividadeCfopResponseDTO responseDTO = conectividadeCfopService.processor(requestDTO);
        assertNotNull(responseDTO);
        assertEquals(requestDTO.getClientMessage(), "SUCCESS");
    }

}
