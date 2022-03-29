package com.baggio.projeto.masterfinanceapi.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.entities.PessoaFisica;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;

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

	public PessoaFisicaDTO(Long id, String nome, String email, String telefone, String celular, TipoPessoa tipoPessoa, String cpf, Instant dataNascimento) {
		super(id, nome, email, telefone, celular, tipoPessoa);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
		
	public PessoaFisicaDTO(PessoaFisica pessoaFisica) {
		super(pessoaFisica.getId(), pessoaFisica.getNome(), 
				pessoaFisica.getEmail(), pessoaFisica.getTelefone(), 
				pessoaFisica.getCelular(), pessoaFisica.getTipoPessoa());
		cpf = pessoaFisica.getCpf();
		dataNascimento = pessoaFisica.getDataNascimento();
	}
	
}
