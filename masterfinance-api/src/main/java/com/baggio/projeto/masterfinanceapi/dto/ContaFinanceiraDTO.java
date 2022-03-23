package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.ContaFinanceira;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaFinanceiraDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String descricao;

	@NotNull(message = "Campo obrigatório")
	private BigDecimal saldo;
	
	public ContaFinanceiraDTO() {

	}

	public ContaFinanceiraDTO(Long id, String descricao, BigDecimal saldo) {
		this.id = id;
		this.descricao = descricao;
		this.saldo = saldo;
	}

	public ContaFinanceiraDTO(ContaFinanceira contaFinanceira) {
		this.id = contaFinanceira.getId();
		this.descricao = contaFinanceira.getDescricao();
		this.saldo = contaFinanceira.getSaldo();
	}
	
}
