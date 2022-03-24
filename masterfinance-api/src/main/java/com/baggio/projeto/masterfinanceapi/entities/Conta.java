package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_conta")
@Inheritance(strategy = InheritanceType.JOINED)
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private BigDecimal saldo;

	public Conta() {
	}

	public Conta(Long id, String descricao, BigDecimal saldo) {
		this.id = id;
		this.descricao = descricao;
		this.saldo = saldo;
	}

}
