package com.baggio.projeto.masterfinanceapi.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_conta_bancaria")
@PrimaryKeyJoinColumn( name = "id")
public class ContaBancaria extends Conta{
	
	private static final long serialVersionUID = 1L;

	private String numero;
	
	private String digito;
	
	private String agencia;
	
	@Column(name = "digito_agencia")
	private String digitoAgencia;
	
	@ManyToOne
	@JoinColumn(name = "banco_id")
	private Banco banco;
	
	public ContaBancaria() {
	}

	public ContaBancaria(Long id, String descricao, BigDecimal saldo, String numero, String digito, String agencia,
			String digitoAgencia, Banco banco) {
		super(id, descricao, saldo);
		this.numero = numero;
		this.digito = digito;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.banco = banco;
	}

	

}
