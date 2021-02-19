package br.com.totvs.tcb.cobranca.controller.dto.response.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
@NoArgsConstructor
public class ConectividadeCfopResponseDTO extends BaseCfopResponseDTO {

    private String clientMessage;
    private String serverMessage;

    public ConectividadeCfopResponseDTO(String clientMessage, String serverMessage) {
        this.clientMessage = clientMessage;
        this.serverMessage = serverMessage;
    }

}
