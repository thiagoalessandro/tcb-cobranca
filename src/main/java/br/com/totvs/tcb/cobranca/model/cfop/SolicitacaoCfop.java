package br.com.totvs.tcb.cobranca.model.cfop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "tbl_solicitacao_cfop")
@NoArgsConstructor
public class SolicitacaoCfop extends AbstractCfopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sol_cfop", nullable = false)
    private Long id;

    @NotNull(message = "Id Conta integradora é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cnta_intg_cfop", nullable = false)
    private ContaIntegradoraCfop contaIntegradora;

    @NotNull(message = "Descrição da transação é obrigatória")
    @Column(name = "ds_trn", nullable = false)
    private String descricaoTransacao;

    @NotNull(message = "Codigo moeda é obrigatório")
    @Column(name = "cd_moeda", nullable = false)
    private String codigoMoeda;

    @NotNull(message = "Valor da solicição é obrigatório")
    @Column(name = "vr_sol", nullable = false)
    private BigDecimal valorSolicitacao;

    @Column(name = "num_ref")
    private String numeroReferencia;

}
