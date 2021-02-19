package br.com.totvs.tcb.cobranca.controller.dto.request.cfop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class BaseCfopRequestDTO implements Serializable {

    private HeaderCfopRequestDTO requestHeader;

}
