package br.com.totvs.tcb.cobranca.model.cfop;

import br.com.totvs.tcb.cobranca.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "tbl_conta_integradora_cfop")
@NoArgsConstructor
public class ContaIntegradoraCfop extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cnta_intg_cfop", nullable = false)
    private Long id;

    @NotNull(message = "Conta integradora é obrigatória")
    @Column(name = "cd_cnta_intg", nullable = false)
    private String contaIntegradora;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_cad")
    private Date dataHoraCadastro;

}
