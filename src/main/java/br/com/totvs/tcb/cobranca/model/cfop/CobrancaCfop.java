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
@ToString(callSuper = true)
@Table(name = "tbl_cobranca_cfop")
@NoArgsConstructor
public class CobrancaCfop extends AbstractCfopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cob_cfop", nullable = false)
    private Long id;

    @NotNull(message = "Id Conta integradora é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cnta_intg_cfop", nullable = false)
    private ContaIntegradoraCfop contaIntegradora;

    @NotNull(message = "Descrição da transação é obrigatória")
    @Column(name = "ds_transacao", nullable = false)
    private String descricaoTransacao;

    @NotNull(message = "Codigo moeda é obrigatório")
    @Column(name = "cd_moeda", nullable = false)
    private String codigoMoeda;

    @NotNull(message = "Valor da cobrança é obrigatório")
    @Column(name = "vr_cobranca", nullable = false)
    private BigDecimal valorCobranca;

    @Column(name = "linha_digitavel")
    private String linhaDigitavel;

    @Column(name = "cod_barras")
    private String codigoBarras;

    @Column(name = "nosso_numero")
    private String nossoNumero;

}
