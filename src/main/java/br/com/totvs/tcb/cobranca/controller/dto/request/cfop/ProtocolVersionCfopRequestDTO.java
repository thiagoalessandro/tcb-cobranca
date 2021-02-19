package br.com.totvs.tcb.cobranca.controller.dto.request.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@ApiModel
@NoArgsConstructor
public class ProtocolVersionCfopRequestDTO implements Serializable {

    private Integer major;
    private Integer minor;
    private Integer revision;

}
