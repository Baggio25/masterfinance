package com.baggio.projeto.masterfinanceapi.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_conta_financeira")
public class ContaFinanceira extends Conta{
	
	private static final long serialVersionUID = 1L;
	
	public ContaFinanceira() {
	}
	
}
