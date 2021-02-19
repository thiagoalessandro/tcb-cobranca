package br.com.totvs.tcb.cobranca.controller.dto.response.cfop;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@ApiModel
public class HeaderCfopResponseDTO implements Serializable {

    private Long responseTimestamp;

    public HeaderCfopResponseDTO() {
        this.responseTimestamp = new Date().getTime();
    }

}
