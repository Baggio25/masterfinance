package com.baggio.projeto.masterfinanceapi.dto;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.entities.PessoaJuridica;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;

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

	public PessoaJuridicaDTO(Long id, String nome, String email, String telefone, String celular, TipoPessoa tipoPessoa,
			String razaoSocial, String cnpj) {
		super(id, nome, email, telefone, celular, tipoPessoa);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}
	
	public PessoaJuridicaDTO(PessoaJuridica pessoaJuridica) {
		super(pessoaJuridica.getId(), pessoaJuridica.getNome(), pessoaJuridica.getEmail(), 
				pessoaJuridica.getTelefone(), pessoaJuridica.getCelular(), pessoaJuridica.getTipoPessoa());
		razaoSocial = pessoaJuridica.getRazaoSocial();
		cnpj = pessoaJuridica.getCnpj();
	}
	
}
