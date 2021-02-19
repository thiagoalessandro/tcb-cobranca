package br.com.totvs.tcb.cobranca.controller.dto.response.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
@NoArgsConstructor
public class SolicitacaoCfopResponseDTO extends BaseCfopResponseDTO {

    private String referenceNumber;

}
