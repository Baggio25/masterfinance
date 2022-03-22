package com.baggio.projeto.masterfinanceapi.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_fisica")
@PrimaryKeyJoinColumn( name = "id")
public class PessoaFisica extends Pessoa{
	
	private static final long serialVersionUID = 1L;

	private String cpf;
	
	@Column(name = "data_nascimento", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataNascimento;
	
	public PessoaFisica() {

	}

	public PessoaFisica(Long id, String nome, String email, String telefone, String celular, String cpf,
			Instant dataNascimento) {
		super(id, nome, email, telefone, celular);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
}
