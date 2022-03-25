package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.Cidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@NotNull(message = "Campo obrigatório")
	private Long estadoId;
	
	public CidadeDTO() {
	}

	public CidadeDTO(Long id, String nome, Long estadoId) {
		this.id = id;
		this.nome = nome;
		this.estadoId = estadoId;
	}

	public CidadeDTO(Cidade cidade) {
		id = cidade.getId();
		nome = cidade.getNome();
		estadoId = cidade.getEstado().getId();
	}
	
}
