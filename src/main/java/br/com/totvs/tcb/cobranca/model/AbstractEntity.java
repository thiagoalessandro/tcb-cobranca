package br.com.totvs.tcb.cobranca.model;

import br.com.totvs.tcb.cobranca.domain.DominioSituacaoRegistro;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Log4j2
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_sit", length = 1)
    private DominioSituacaoRegistro situacao;
    @Column(name = "cd_usu_atu", length = 25)
    private String codigoUsuarioAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_atu")
    private Date dataHoraAtualizacao;

    @PrePersist
    public void prePersist() {
        dataHoraAtualizacao = new Date();
        situacao = DominioSituacaoRegistro.ATIVO;
    }

    @PreUpdate
    public void preUpdate() {
        dataHoraAtualizacao = new Date();
    }

    @PreRemove
    public void preRemove() {
        dataHoraAtualizacao = new Date();
    }

}
