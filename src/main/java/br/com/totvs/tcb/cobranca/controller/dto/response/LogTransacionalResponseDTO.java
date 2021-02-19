package br.com.totvs.tcb.cobranca.controller.dto.response;

import br.com.totvs.tcb.cobranca.domain.DominioRecurso;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class LogTransacionalResponseDTO {

    @ApiModelProperty
    private Long id;

    @JsonProperty(value = "dominio")
    private DominioRecurso dominio;

    @JsonProperty(value = "chave")
    private Long chave;

    @JsonProperty(value = "requestId")
    private String requestId;

    @JsonProperty(value = "traceId")
    private String traceId;

    @JsonProperty(value = "requestPath")
    private String requestPath;

    @JsonProperty(value = "responseStatus")
    private Integer responseStatus;

    @JsonProperty(value = "responseErrors")
    private String responseErrors;

    @JsonProperty(value = "exception")
    private String exception;

    @JsonProperty(value = "requestMethod")
    private String requestMethod;

    @JsonProperty(value = "requestQuery")
    private String requestQuery;

    @JsonProperty(value = "ip")
    private String ip;

    @JsonProperty(value = "usuario")
    private String usuario;

    @JsonProperty(value = "dataHoraInicio")
    private Date dataHoraInicio;

    @JsonProperty(value = "dataHoraFim")
    private Date dataHoraFim;

    @JsonProperty(value = "tempoRespostaMs")
    private Integer tempoRespostaMs;

}
