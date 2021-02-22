package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.client.processador.service.ProcessadorClientService;
import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.CobrancaCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.CobrancaCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import br.com.totvs.tcb.cobranca.encryptor.pgp.PgpEncryptor;
import br.com.totvs.tcb.cobranca.exceptions.ApiException;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.fixture.dto.request.client.processador.CobrancaCfopRequestDTOFixture;
import br.com.totvs.tcb.cobranca.fixture.dto.response.client.processador.TituloCobrancaResponseDTOFixture;
import br.com.totvs.tcb.cobranca.fixture.model.CobrancaCfopFixture;
import br.com.totvs.tcb.cobranca.fixture.model.ContaIntegradoraCfopFixture;
import br.com.totvs.tcb.cobranca.repository.CobrancaCfopRepository;
import br.com.totvs.tcb.cobranca.service.LogTransacionalService;
import br.com.totvs.tcb.cobranca.utils.ModelMapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CobrancaCfopService.class, PgpEncryptor.class, ModelMapperUtils.class})
public class CobrancaCfopServiceTest {

    @Autowired
    private CobrancaCfopService cobrancaCfopService;

    @MockBean
    private CobrancaCfopRepository cobrancaCfopRepository;

    @MockBean
    private ProcessadorClientService processadorClientService;

    @MockBean
    private ContaIntegradoraCfopService contaIntegradoraCfopService;

    @MockBean
    private PgpEncryptor pgpEncryptor;

    @MockBean
    private LogTransacionalService logTransacionalService;

    @Test
    public void solicitacaoCfopSucesso() {
        try {
            final TituloCobrancaResponseDTO tituloCobrancaResponseDTO = TituloCobrancaResponseDTOFixture.valido();
            final CobrancaCfopRequestDTO requestDTO = CobrancaCfopRequestDTOFixture.valido();

            when(contaIntegradoraCfopService.findByContaIntegradora(any())).thenReturn(Optional.of(ContaIntegradoraCfopFixture.valido()));
            when(processadorClientService.registrarCobranca(any())).thenReturn(tituloCobrancaResponseDTO);
            when(cobrancaCfopRepository.save(any())).thenReturn(CobrancaCfopFixture.valido());

            CobrancaCfopResponseDTO responseDTO = cobrancaCfopService.processor(requestDTO);

            verify(contaIntegradoraCfopService).findByContaIntegradora(any());
            verify(cobrancaCfopRepository).save(any());
            verify(processadorClientService).registrarCobranca(any());

            assertNotNull(requestDTO);
            assertEquals(responseDTO.getReferenceNumber(), tituloCobrancaResponseDTO.getLinhaDigitavel());

        } catch (CfopException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void solicitacaoCfopContaIntegradoraInvalida() {
        try {
            CobrancaCfopRequestDTO requestDTO = CobrancaCfopRequestDTOFixture.valido();
            cobrancaCfopService.processor(requestDTO);
        } catch (CfopException e) {
            assertEquals(e.getMessage(), ErrorCfopEnum.INVALID_IDENTIFIER.name());
        }
    }

}
