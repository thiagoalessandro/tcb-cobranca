package br.com.totvs.tcb.cobranca.controller.dto.request.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
@NoArgsConstructor
public class CobrancaCfopRequestDTO extends BaseCfopRequestDTO {

    private String paymentIntegratorAccountId;
    private String transactionDescription;
    private String currencyCode;
    private Long amount;

}
