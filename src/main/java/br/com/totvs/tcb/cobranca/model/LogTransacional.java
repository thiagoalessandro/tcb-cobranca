package br.com.totvs.tcb.cobranca.model;

import br.com.totvs.tcb.cobranca.domain.DominioRecurso;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_log_transacional")
public class LogTransacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dominio", length = 15)
    private DominioRecurso dominio;

    @Column(name = "chave")
    private Long chave;

    @Column(name = "req_id")
    private String requestId;

    @Column(name = "trace_id", length = 15, nullable = false)
    private Long traceId;

    @Column(name = "req_path")
    private String requestPath;

    @Column(name = "resp_status")
    private Integer responseStatus;

    @Column(name = "resp_errors")
    private String responseErrors;

    @Column(name = "exception")
    private String exception;

    @Column(name = "req_method", length = 10)
    private String requestMethod;

    @Column(name = "req_query")
    private String requestQuery;

    @Column(name = "resp")
    private String response;

    @Column(name = "ip", length = 20)
    private String ip;

    @Column(name = "cd_usu", length = 25)
    private String usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_ini", nullable = false)
    private Date dataHoraInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_fim", nullable = false)
    private Date dataHoraFim;

    @Column(name = "resp_time_ms", nullable = false)
    private Integer tempoRespostaMs;

}
