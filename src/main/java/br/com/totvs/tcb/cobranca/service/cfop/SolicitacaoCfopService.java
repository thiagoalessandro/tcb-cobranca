package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.TituloCobrancaRequestDTO;
import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.client.processador.service.ProcessadorClientService;
import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.SolicitacaoCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.SolicitacaoCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import br.com.totvs.tcb.cobranca.exceptions.ApiException;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.model.cfop.ContaIntegradoraCfop;
import br.com.totvs.tcb.cobranca.model.cfop.SolicitacaoCfop;
import br.com.totvs.tcb.cobranca.repository.SolicitacaoCfopRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SolicitacaoCfopService extends AbstractCfopService<SolicitacaoCfopRepository, SolicitacaoCfop, SolicitacaoCfopRequestDTO, SolicitacaoCfopResponseDTO> {

    private final ContaIntegradoraCfopService contaIntegradoraCfopService;
    private final ProcessadorClientService processadorClientService;

    SolicitacaoCfopService(SolicitacaoCfopRepository repository,
                           ContaIntegradoraCfopService contaIntegradoraCfopService,
                           ProcessadorClientService processadorClientService) {
        super(repository);
        this.contaIntegradoraCfopService = contaIntegradoraCfopService;
        this.processadorClientService = processadorClientService;
    }

    @Override
    protected SolicitacaoCfopResponseDTO processor(SolicitacaoCfopRequestDTO requestDTO) throws CfopException {
        TituloCobrancaResponseDTO tituloCobrancaResponseDTO = null;

        //TODO: Validar dados... conta integradora etc.
        ContaIntegradoraCfop contaIntegradora = validaSolicitacao(requestDTO);
        SolicitacaoCfop solicitacaoCfop = mapRequestToEntity(requestDTO);
        solicitacaoCfop.setContaIntegradora(contaIntegradora);

        solicitacaoCfop = repository.save(solicitacaoCfop);

        //TODO: Prerarar requisição ao processador
        TituloCobrancaRequestDTO tituloCobrancaRequestDTO = preparaSolicitacaoProcessador(solicitacaoCfop);

        //TODO: Enviar Solicitação ao processador
        tituloCobrancaResponseDTO = registrarCobranca(tituloCobrancaRequestDTO);

        //TODO: Recuperar código de barras ou linha digitável
        solicitacaoCfop.setNumeroReferencia(tituloCobrancaResponseDTO.getCodigoBarras());

        return mapEntityToResponse(solicitacaoCfop);
    }

    private TituloCobrancaResponseDTO registrarCobranca(TituloCobrancaRequestDTO tituloCobrancaRequestDTO) throws CfopException {
        TituloCobrancaResponseDTO tituloCobrancaResponseDTO;
        try {
            tituloCobrancaResponseDTO = processadorClientService.registrarCobranca(tituloCobrancaRequestDTO);
        } catch (ApiException e) {
            log.error("Ocorreu um erro ao enviar requisição ao processador: {}", e.getMessage());
            throw new CfopException(e, HttpStatus.BAD_REQUEST.value());
        }
        return tituloCobrancaResponseDTO;
    }

    protected TituloCobrancaRequestDTO preparaSolicitacaoProcessador(SolicitacaoCfop solicitacaoCfop) {
        TituloCobrancaRequestDTO tituloCobrancaRequestDTO = new TituloCobrancaRequestDTO();
        //TODO: Recuperar informações da conta
        return tituloCobrancaRequestDTO;
    }

    protected ContaIntegradoraCfop validaSolicitacao(SolicitacaoCfopRequestDTO requestDTO) throws CfopException {
        return contaIntegradoraCfopService.findByContaIntegradora(requestDTO.getPaymentIntegratorAccountId())
                .orElseThrow(() -> new CfopException(ErrorCfopEnum.INVALID_IDENTIFIER));
    }

    @Override
    protected PropertyMap<SolicitacaoCfopRequestDTO, SolicitacaoCfop> mapRequestToEntity() {
        return new PropertyMap<SolicitacaoCfopRequestDTO, SolicitacaoCfop>() {
            protected void configure() {
                map(source.getPaymentIntegratorAccountId(), destination.getContaIntegradora());
                map(source.getTransactionDescription(), destination.getDescricaoTransacao());
                map(source.getCurrencyCode(), destination.getCodigoMoeda());
                map(source.getAmount(), destination.getValorSolicitacao());

                map(source.getRequestHeader().getRequestId(), destination.getIdRequisicao());
                map(source.getRequestHeader().getRequestTimestamp(), destination.getDataHoraRequisicao());
                map(source.getRequestHeader().getProtocolVersion().getMajor(), destination.getProtocoloVersaoMaior());
                map(source.getRequestHeader().getProtocolVersion().getMinor(), destination.getProtocoloVersaoMenor());
                map(source.getRequestHeader().getProtocolVersion().getRevision(), destination.getProtocoloRevisao());
            }
        };
    }

    @Override
    protected PropertyMap<SolicitacaoCfop, SolicitacaoCfopResponseDTO> mapEntityToResponse() {
        return new PropertyMap<SolicitacaoCfop, SolicitacaoCfopResponseDTO>() {
            protected void configure() {
                map(source.getNumeroReferencia(), destination.getReferenceNumber());
            }
        };
    }
}
