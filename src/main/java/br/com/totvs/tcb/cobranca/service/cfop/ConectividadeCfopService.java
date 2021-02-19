package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.ConectividadeCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.ConectividadeCfopResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ConectividadeCfopService extends AbstractCfopExecutor<ConectividadeCfopRequestDTO, ConectividadeCfopResponseDTO> {

    @Override
    protected ConectividadeCfopResponseDTO processor(ConectividadeCfopRequestDTO requestDTO) {
        String serverMessage = "v1.echo server message TCB";
        return new ConectividadeCfopResponseDTO(requestDTO.getClientMessage(), serverMessage);
    }
}
