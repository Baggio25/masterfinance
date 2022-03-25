package com.baggio.projeto.masterfinanceapi.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.entities.PessoaFisica;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFisicaDTO extends Pessoa{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo obrigatório")	
	private String cpf;
	
	private Instant dataNascimento;
	
	
	public PessoaFisicaDTO() {
	}


	public PessoaFisicaDTO(String cpf, Instant dataNascimento) {
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	public PessoaFisicaDTO(PessoaFisica pessoaFisica) {
		cpf = pessoaFisica.getCpf();
		dataNascimento = pessoaFisica.getDataNascimento();
	}
	
}
