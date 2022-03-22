package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.entities.enums.StatusPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_conta_pagar")
public class ContaPagar implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	
	private String parcela;
	
	private String descricao;
	
	private BigDecimal valor;
	
	@Column(name = "saldo_pagar")
	private BigDecimal saldoPagar;
	
	@Column(name = "data_vencimento", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataVencimento;
	
	@Column(name = "data_pagamento", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataPagamento;
	
	@Column(columnDefinition = "TEXT")
	private String obervacao;
	
	@Enumerated
	@Column(name = "status_pagamento")
	private StatusPagamento statusPagamento;	
	
	private String anexo;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(name = "historico_financeiro_id")
	private HistoricoFinanceiro historicoFinanceiro;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
}





