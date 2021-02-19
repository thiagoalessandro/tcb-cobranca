package br.com.totvs.tcb.cobranca.client.processador.service;

import br.com.totvs.tcb.cobranca.client.AbstractClient;
import br.com.totvs.tcb.cobranca.client.processador.dto.request.TituloCobrancaRequestDTO;
import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.client.processador.feign.ProcessadorClient;
import br.com.totvs.tcb.cobranca.exceptions.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessadorClientService extends AbstractClient {

    private final ProcessadorClient processadorClient;

    public ProcessadorClientService(ProcessadorClient processadorClient) {
        this.processadorClient = processadorClient;
    }

    public TituloCobrancaResponseDTO registrarCobranca(TituloCobrancaRequestDTO requestDTO) throws ApiException {
        try {
            return processadorClient.registrar(requestDTO).getData();
        } catch (FeignException e) {
            responseHandler(e);
        }
        return null;
    }
}
