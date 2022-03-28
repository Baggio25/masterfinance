package com.baggio.projeto.masterfinanceapi.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.dto.ContaBancariaDTO;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_conta_bancaria")
public class ContaBancaria extends Conta implements Convertible<ContaBancariaDTO>{
	
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

	public ContaBancaria(Long id, String descricao, BigDecimal saldo, Boolean bancaria, String numero, String digito, String agencia,
			String digitoAgencia, Banco banco) {
		super(id, descricao, saldo, bancaria);
		this.numero = numero;
		this.digito = digito;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.banco = banco;
	}

	@Override
	public ContaBancariaDTO convert() {
		return new ContaBancariaDTO(this);
	}

}
