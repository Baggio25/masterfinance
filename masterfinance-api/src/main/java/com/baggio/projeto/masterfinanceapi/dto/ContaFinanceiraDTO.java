package com.baggio.projeto.masterfinanceapi.dto;

import com.baggio.projeto.masterfinanceapi.entities.ContaFinanceira;

public class ContaFinanceiraDTO extends ContaDTO {

	private static final long serialVersionUID = 1L;
	
	public ContaFinanceiraDTO() {

	}
		
	public ContaFinanceiraDTO(ContaFinanceira contaFinanceira) {
		super(contaFinanceira.getId(), contaFinanceira.getDescricao(), contaFinanceira.getSaldo());
	}
}
