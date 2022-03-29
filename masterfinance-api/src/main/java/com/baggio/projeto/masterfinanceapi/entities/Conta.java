package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_conta")
@SequenceGenerator(name = "seq_conta", sequenceName = "seq_conta", allocationSize = 1, initialValue = 1)
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta")
	private Long id;

	private String descricao;

	private BigDecimal saldo;
	
	private Boolean bancaria;

	public Conta() {
	}

	public Conta(Long id, String descricao, BigDecimal saldo, Boolean bancaria) {
		this.id = id;
		this.descricao = descricao;
		this.saldo = saldo;
		this.bancaria = bancaria;
	}

}
