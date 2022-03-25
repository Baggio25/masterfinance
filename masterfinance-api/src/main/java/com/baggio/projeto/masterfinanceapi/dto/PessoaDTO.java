package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private String celular;
	
	private TipoPessoa tipoPessoa;
	
	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, String email, String telefone,
			String celular, TipoPessoa tipoPessoa) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.tipoPessoa = tipoPessoa;
	}

	public PessoaDTO(Pessoa pessoa) {
		id = pessoa.getId();
		nome = pessoa.getNome();
		email = pessoa.getEmail();
		telefone = pessoa.getTelefone();
		celular = pessoa.getCelular();
		tipoPessoa = pessoa.getTipoPessoa();
	}
	
}
