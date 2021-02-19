package br.com.totvs.tcb.cobranca.controller.dto.request.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
@NoArgsConstructor
public class ConectividadeCfopRequestDTO extends BaseCfopRequestDTO {

    private String clientMessage;

}
