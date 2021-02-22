package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.*;
import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.client.processador.service.ProcessadorClientService;
import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.CobrancaCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.CobrancaCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import br.com.totvs.tcb.cobranca.domain.DominioEspecieTitulo;
import br.com.totvs.tcb.cobranca.domain.DominioMoedaTitulo;
import br.com.totvs.tcb.cobranca.domain.DominioTipoInscricao;
import br.com.totvs.tcb.cobranca.exceptions.ApiException;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.model.cfop.ContaIntegradoraCfop;
import br.com.totvs.tcb.cobranca.model.cfop.CobrancaCfop;
import br.com.totvs.tcb.cobranca.repository.CobrancaCfopRepository;
import br.com.totvs.tcb.cobranca.utils.CfopUtils;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log4j2
@Service
public class CobrancaCfopService extends AbstractCfopService<CobrancaCfopRepository, CobrancaCfop, CobrancaCfopRequestDTO, CobrancaCfopResponseDTO> {

    private final ContaIntegradoraCfopService contaIntegradoraCfopService;
    private final ProcessadorClientService processadorClientService;

    CobrancaCfopService(CobrancaCfopRepository repository,
                        ContaIntegradoraCfopService contaIntegradoraCfopService,
                        ProcessadorClientService processadorClientService) {
        super(repository);
        this.contaIntegradoraCfopService = contaIntegradoraCfopService;
        this.processadorClientService = processadorClientService;
    }

    @Override
    protected CobrancaCfopResponseDTO processor(CobrancaCfopRequestDTO requestDTO) throws CfopException {
        TituloCobrancaResponseDTO tituloCobrancaResponseDTO;
        ContaIntegradoraCfop contaIntegradora = validaSolicitacao(requestDTO);
        CobrancaCfop cobrancaCfop = mapRequestToEntity(requestDTO);
        cobrancaCfop.setContaIntegradora(contaIntegradora);
        TituloCobrancaRequestDTO tituloCobrancaRequestDTO = preparaSolicitacaoProcessador(cobrancaCfop);
        tituloCobrancaResponseDTO = registrarCobranca(tituloCobrancaRequestDTO);
        salvaDadosCobranca(tituloCobrancaResponseDTO, cobrancaCfop);
        return mapEntityToResponse(cobrancaCfop);
    }

    private void salvaDadosCobranca(TituloCobrancaResponseDTO responseDTO, CobrancaCfop model) throws CfopException {
        try {
            log.info("Salvando cobrança...");
            model.setLinhaDigitavel(responseDTO.getLinhaDigitavel());
            model.setCodigoBarras(responseDTO.getCodigoBarras());
            model.setNossoNumero(responseDTO.getNossoNumero());
            log.debug(model.toString());
            repository.save(model);
        } catch (Exception e) {
            log.error("Não foi possível cadastrar a cobrança", e);
            throw new CfopException(e, HttpStatus.BAD_REQUEST.value());
        }
    }

    private TituloCobrancaResponseDTO registrarCobranca(TituloCobrancaRequestDTO tituloCobrancaRequestDTO) throws CfopException {
        TituloCobrancaResponseDTO tituloCobrancaResponseDTO;
        try {
            log.info("Registrando cobrança...");
            tituloCobrancaResponseDTO = processadorClientService.registrarCobranca(tituloCobrancaRequestDTO);
            log.debug(tituloCobrancaResponseDTO.toString());
        } catch (ApiException e) {
            log.error("Ocorreu um erro ao enviar requisição ao processador: {}", e.getMessage());
            throw new CfopException(e, HttpStatus.BAD_REQUEST.value());
        }
        return tituloCobrancaResponseDTO;
    }

    protected TituloCobrancaRequestDTO preparaSolicitacaoProcessador(CobrancaCfop cobrancaCfop) throws CfopException {
        TituloCobrancaRequestDTO tituloCobrancaRequestDTO = new TituloCobrancaRequestDTO();
        Faker faker = new Faker();
        try {
            log.info("Preparando requisição ao processador...");
            ControleRequestDTO controle = new ControleRequestDTO();
            controle.setUuid(cobrancaCfop.getIdRequisicao());
            controle.setDataHoraEvento(cobrancaCfop.getDataHoraRequisicao());
            tituloCobrancaRequestDTO.setControle(controle);

            //TODO: Deverá recuperar dos dados do cedente (GOOGLE) por meio da conta integradora
            CedenteTituloRequestDTO cedente = new CedenteTituloRequestDTO();
            cedente.setNome("GOOGLE BRASIL INTERNET LTDA.");
            cedente.setTipoInscricao(DominioTipoInscricao.CNPJ);
            cedente.setNumeroInscricao("06990590000123");
            tituloCobrancaRequestDTO.setCedente(cedente);

            SacadoTituloRequestDTO sacado = new SacadoTituloRequestDTO();
            sacado.setNome(faker.name().fullName());
            sacado.setTipoInscricao(DominioTipoInscricao.CPF);
            sacado.setNumeroInscricao("94792316898");
            sacado.setUf("SP");
            sacado.setCidade("Anhembi");
            sacado.setCep("18620970");
            sacado.setBairro("Centro");
            sacado.setEndereco("Rua Marechal Deodoro 88");
            tituloCobrancaRequestDTO.setSacado(sacado);

            tituloCobrancaRequestDTO.setNumeroDocumentoCobranca(faker.number().digits(15));
            tituloCobrancaRequestDTO.setDataEmissao(new Date());
            tituloCobrancaRequestDTO.setDataVencimento(new Date());
            tituloCobrancaRequestDTO.setValorTitulo(cobrancaCfop.getValorCobranca());
            tituloCobrancaRequestDTO.setEspecieTitulo(DominioEspecieTitulo.DUPLICATA_SERVICO);
            tituloCobrancaRequestDTO.setInstrucoes(new InstrucoesTituloRequestDTO());
            tituloCobrancaRequestDTO.setCodigoMoeda(DominioMoedaTitulo.valueOf(cobrancaCfop.getCodigoMoeda()).getValue());

            log.debug(tituloCobrancaRequestDTO.toString());

            return tituloCobrancaRequestDTO;
        } catch (Exception e) {
            log.error("Ocorreu um erro ao preparar requisição ao processador", e);
            throw new CfopException(e, HttpStatus.BAD_REQUEST.value());
        }
    }

    protected ContaIntegradoraCfop validaSolicitacao(CobrancaCfopRequestDTO requestDTO) throws CfopException {
        log.info("Validando conta integradora {}...", requestDTO.getPaymentIntegratorAccountId());
        return contaIntegradoraCfopService.findByContaIntegradora(requestDTO.getPaymentIntegratorAccountId())
                .orElseThrow(() -> new CfopException(ErrorCfopEnum.INVALID_IDENTIFIER));
    }

    @Override
    protected PropertyMap<CobrancaCfopRequestDTO, CobrancaCfop> mapRequestToEntity() {
        return new PropertyMap<CobrancaCfopRequestDTO, CobrancaCfop>() {
            protected void configure() {
                map(source.getPaymentIntegratorAccountId(), destination.getContaIntegradora());
                map(source.getTransactionDescription(), destination.getDescricaoTransacao());
                map(source.getCurrencyCode(), destination.getCodigoMoeda());
                map(source.getAmount(), destination.getValorCobranca());

                map(source.getRequestHeader().getRequestId(), destination.getIdRequisicao());
                map(source.getRequestHeader().getRequestTimestamp(), destination.getDataHoraRequisicao());
                map(source.getRequestHeader().getProtocolVersion().getMajor(), destination.getProtocoloVersaoMaior());
                map(source.getRequestHeader().getProtocolVersion().getMinor(), destination.getProtocoloVersaoMenor());
                map(source.getRequestHeader().getProtocolVersion().getRevision(), destination.getProtocoloRevisao());
            }
        };
    }

    @Override
    protected PropertyMap<CobrancaCfop, CobrancaCfopResponseDTO> mapEntityToResponse() {
        return new PropertyMap<CobrancaCfop, CobrancaCfopResponseDTO>() {
            protected void configure() {
                map(source.getLinhaDigitavel(), destination.getReferenceNumber());
            }
        };
    }
}
