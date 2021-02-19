package br.com.totvs.tcb.cobranca.controller.dto.request.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel
@NoArgsConstructor
public class HeaderCfopRequestDTO implements Serializable {

    private ProtocolVersionCfopRequestDTO protocolVersion;
    private String requestId;
    private Long requestTimestamp;

}
