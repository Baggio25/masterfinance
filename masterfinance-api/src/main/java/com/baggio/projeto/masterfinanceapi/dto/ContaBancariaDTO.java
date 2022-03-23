package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.ContaBancaria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaBancariaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo obrigatório")
	private String descricao;

	@NotNull(message = "Campo obrigatório")
	private BigDecimal saldo;

	@NotNull(message = "Campo obrigatório")
	private String numero;

	@NotNull(message = "Campo obrigatório")
	private String digito;

	@NotNull(message = "Campo obrigatório")
	private String agencia;

	@NotNull(message = "Campo obrigatório")
	private String digitoAgencia;
	
	private Long bancoId;

	public ContaBancariaDTO() {

	}

	public ContaBancariaDTO(Long id, String descricao, BigDecimal saldo, String numero, String digito,
			String agencia, String digitoAgencia, Long bancoId) {
		this.id = id;
		this.descricao = descricao;
		this.saldo = saldo;
		this.numero = numero;
		this.digito = digito;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.bancoId = bancoId;
	}


	public ContaBancariaDTO(ContaBancaria contaBancaria) {
		this.id = contaBancaria.getId();
		this.descricao = contaBancaria.getDescricao();
		this.saldo = contaBancaria.getSaldo();
		this.numero = contaBancaria.getNumero();
		this.digito = contaBancaria.getDigito();
		this.agencia = contaBancaria.getAgencia();
		this.digitoAgencia = contaBancaria.getDigitoAgencia();
		this.bancoId = contaBancaria.getBanco().getId();
	}

}
