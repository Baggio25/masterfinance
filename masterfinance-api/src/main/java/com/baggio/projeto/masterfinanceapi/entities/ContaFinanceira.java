package com.baggio.projeto.masterfinanceapi.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.dto.ContaFinanceiraDTO;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_conta_financeira")
public class ContaFinanceira extends Conta implements Convertible<ContaFinanceiraDTO>{
	
	private static final long serialVersionUID = 1L;
	
	public ContaFinanceira() {
	}

	@Override
	public ContaFinanceiraDTO convert() {
		return new ContaFinanceiraDTO(this);
	}
	
}
