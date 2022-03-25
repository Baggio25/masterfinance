package com.baggio.projeto.masterfinanceapi.dto;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.entities.PessoaJuridica;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaJuridicaDTO extends Pessoa{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo obrigatório")	
	private String razaoSocial;
	
	private String cnpj;
	
	
	public PessoaJuridicaDTO() {
	}


	public PessoaJuridicaDTO(String razaoSocial, String cnpj) {
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	public PessoaJuridicaDTO(PessoaJuridica pessoaJuridica) {
		razaoSocial = pessoaJuridica.getRazaoSocial();
		cnpj = pessoaJuridica.getCnpj();
	}
	
	
}
