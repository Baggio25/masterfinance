package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_movimento_avulso")
@SequenceGenerator(name = "seq_movimento_avulso", sequenceName = "seq_movimento_avulso", allocationSize = 1, initialValue = 1)
public class MovimentoAvulso implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimento_avulso")
	private Long id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	@Column(name = "data_movimento", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataMovimento;
	
	@Column(name = "data_competencia", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataCompetencia;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(name = "historico_financeiro_id")
	private HistoricoFinanceiro historicoFinanceiro;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
}





