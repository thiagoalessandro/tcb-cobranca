package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.TcbCobrancaApplicationTests;
import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.client.processador.service.ProcessadorClientService;
import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.SolicitacaoCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.SolicitacaoCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import br.com.totvs.tcb.cobranca.exceptions.ApiException;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.fixture.dto.request.SolicitacaoCfopRequestDTOFixture;
import br.com.totvs.tcb.cobranca.fixture.dto.response.CobrancaBoletoResponseDTOFixture;
import br.com.totvs.tcb.cobranca.fixture.model.ContaIntegradoraCfopFixture;
import br.com.totvs.tcb.cobranca.fixture.model.SolicitacaoCfopFixture;
import br.com.totvs.tcb.cobranca.repository.SolicitacaoCfopRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SolicitacaoCfopServiceTest extends TcbCobrancaApplicationTests {

    @Autowired
    private SolicitacaoCfopService solicitacaoCfopService;

    @MockBean
    private SolicitacaoCfopRepository solicitacaoCfopRepository;

    @MockBean
    private ProcessadorClientService processadorClientService;

    @MockBean
    private ContaIntegradoraCfopService contaIntegradoraCfopService;

    @Test
    public void solicitacaoCfopSucesso() {
        try {
            final TituloCobrancaResponseDTO tituloCobrancaResponseDTO = CobrancaBoletoResponseDTOFixture.valido();
            final SolicitacaoCfopRequestDTO requestDTO = SolicitacaoCfopRequestDTOFixture.valido();

            when(contaIntegradoraCfopService.findByContaIntegradora(any())).thenReturn(Optional.of(ContaIntegradoraCfopFixture.valido()));
            when(processadorClientService.registrarCobranca(any())).thenReturn(tituloCobrancaResponseDTO);
            when(solicitacaoCfopRepository.save(any())).thenReturn(SolicitacaoCfopFixture.valido());

            SolicitacaoCfopResponseDTO responseDTO = solicitacaoCfopService.processor(requestDTO);

            verify(contaIntegradoraCfopService).findByContaIntegradora(any());
            verify(solicitacaoCfopRepository).save(any());
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
            SolicitacaoCfopRequestDTO requestDTO = SolicitacaoCfopRequestDTOFixture.valido();
            solicitacaoCfopService.processor(requestDTO);
        } catch (CfopException e) {
            assertEquals(e.getMessage(), ErrorCfopEnum.INVALID_IDENTIFIER.name());
        }
    }

}
