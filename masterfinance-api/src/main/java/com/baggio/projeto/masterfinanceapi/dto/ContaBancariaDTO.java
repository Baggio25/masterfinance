package com.baggio.projeto.masterfinanceapi.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.ContaBancaria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaBancariaDTO extends ContaDTO {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Campo obrigatório")
	private String numero;

	@NotNull(message = "Campo obrigatório")
	private String digito;

	@NotNull(message = "Campo obrigatório")
	private String agencia;

	@NotNull(message = "Campo obrigatório")
	private String digitoAgencia;
	
	private Boolean bancaria = false;

	@NotNull(message = "Campo obrigatório")
	private Long bancoId;

	public ContaBancariaDTO() {

	}

	public ContaBancariaDTO(Long id, String descricao, BigDecimal saldo, String numero, String digito, String agencia,
			String digitoAgencia, Long bancoId) {
		super(id, descricao, saldo);
		this.numero = numero;
		this.digito = digito;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.bancoId = bancoId;
	}

	public ContaBancariaDTO(ContaBancaria contaBancaria) {
		super(contaBancaria.getId(), contaBancaria.getDescricao(), contaBancaria.getSaldo());
		this.numero = contaBancaria.getNumero();
		this.digito = contaBancaria.getDigito();
		this.agencia = contaBancaria.getAgencia();
		this.digitoAgencia = contaBancaria.getDigitoAgencia();
		this.bancoId = contaBancaria.getBanco().getId();
	}

}
