package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.entities.enums.TipoHistoricoFinanceiro;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_historico_financeiro")
@SequenceGenerator(name = "seq_historico_financeiro", sequenceName = "seq_historico_financeiro", allocationSize = 1, initialValue = 1)
public class HistoricoFinanceiro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_financeiro")
	private Long id;

	private String descricao;
	
	@Enumerated
	@Column(name = "tipo_historico_financeiro")
	private TipoHistoricoFinanceiro tipoHistoricoFinanceiro;
	
	private Boolean ativo;
	
}
