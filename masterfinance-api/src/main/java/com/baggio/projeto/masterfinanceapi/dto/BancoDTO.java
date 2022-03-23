package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Banco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BancoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@NotBlank(message = "Campo obrigatório")
	private String numero;

	public BancoDTO() {

	}

	public BancoDTO(Long id, String nome, String numero) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
	}
	
	public BancoDTO(Banco banco) {
		this.id = banco.getId();
		this.nome = banco.getNome();
		this.numero = banco.getNumero();
	}
	
	
}
