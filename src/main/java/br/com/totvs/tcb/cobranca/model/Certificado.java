package br.com.totvs.tcb.cobranca.model;

import br.com.totvs.tcb.cobranca.domain.DominioTipoCertificado;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "tbl_certificado")
@NoArgsConstructor
public class Certificado extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cer", nullable = false)
    private Long id;

    @NotNull(message = "Código Transação é obrigatório")
    @Column(name = "cd_transacao", nullable = false)
    private Long codigoTransacao;

    @NotNull(message = "Tipo Certificado é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tp_certificado", length = 20)
    private DominioTipoCertificado tipoCertificado;

    @NotEmpty(message = "Nome Arquivo é obrigatório")
    @Column(name = "nm_arq_origem", length = 150, nullable = false)
    private String nomeArquivoOrigem;

    @OneToOne
    @JoinColumn(name = "id_log")
    private LogEventoMensageria logEventoMensageria;

    @Lob
    @NotNull(message = "Certificado é obrigatório")
    @Column(name = "certificado", length = 100000)
    private byte[] certificado;

    @Column(name = "senha")
    private String senha;

}
