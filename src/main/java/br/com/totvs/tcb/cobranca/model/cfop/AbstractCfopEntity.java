package br.com.totvs.tcb.cobranca.model.cfop;

import br.com.totvs.tcb.cobranca.model.AbstractEntity;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Log4j2
@Data
@MappedSuperclass
public abstract class AbstractCfopEntity extends AbstractEntity {

    @NotNull(message = "Protocolo revisão é obrigatório")
    @Column(name = "prt_vrs_revisao", nullable = false)
    private Integer protocoloRevisao;

    @NotNull(message = "Protocolo versão menor é obrigatório")
    @Column(name = "prt_vrs_menor", nullable = false)
    private Integer protocoloVersaoMenor;

    @NotNull(message = "Protocolo versão maior é obrigatório")
    @Column(name = "prt_vrs_maior", nullable = false)
    private Integer protocoloVersaoMaior;

    @NotNull(message = "Id da requisição é obrigatório")
    @Column(name = "req_id", nullable = false)
    private String idRequisicao;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Data/Hora requisição é obrigatório")
    @Column(name = "req_time", nullable = false)
    private Date dataHoraRequisicao;

    @NotNull(message = "Trace Id é obrigatório")
    @Column(name = "trace_id", nullable = false)
    private Long traceId;

}
