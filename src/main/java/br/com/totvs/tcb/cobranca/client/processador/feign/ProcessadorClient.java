package br.com.totvs.tcb.cobranca.client.processador.feign;

import br.com.totvs.tcb.cobranca.client.processador.dto.request.TituloCobrancaRequestDTO;
import br.com.totvs.tcb.cobranca.client.processador.dto.response.TituloCobrancaResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${ws.tcb.processador}", name = "ApiTcbProcessador")
public interface ProcessadorClient {

    @PostMapping("/v1/tituloCobranca/registrar")
    Response<TituloCobrancaResponseDTO> registrar(TituloCobrancaRequestDTO request);

}
